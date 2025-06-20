package com.neo.serviceImpl;

import com.neo.bindings.*;
import com.neo.entity.*;
import com.neo.repository.*;
import com.neo.service.DcMgmtService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DcMgmtServiceImpl implements DcMgmtService {
    @Autowired
    private DcCaseRepository caseRepo;
    @Autowired
    private IApplicationRegistrationRepository citizenAppRepo;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private DcIncomeRepository incomeRepository;
    @Autowired
    private DcEducationRepository educationRepository;
    @Autowired
    private DcChildrenRepository dcChildrenRepository;

    @Override
    public Integer generateCaseNo(Integer appId) {
        //load Citizen Data
        Optional<CitizenApplicationEntity> appCitizen = citizenAppRepo.findById(appId);
        if(appCitizen.isPresent()){
            DcCaseEntity caseEntity = new DcCaseEntity();
            caseEntity.setAppId(appId);
            return caseRepo.save(caseEntity).getCaseNo();

        }
        return 0;
    }


    @Override
    public List<String> showAllPlanNames() {
        List<PlanEntity> plansList = planRepository.findAll();
//        get only plansname using stream Api
        List<String> planNamesList = plansList.stream().map(plan->plan.getPlanName()).toList();

        return planNamesList;
    }

    @Override
    public Integer savePlanSelection(PlanSelectionInputs plan) {

        //load DcCaseEntity object
        Optional<DcCaseEntity> caseEntity= caseRepo.findById(plan.getCaseNo());
        if(caseEntity.isPresent()){
            DcCaseEntity caseEntity1 = caseEntity.get();
            caseEntity1.setPlanId(plan.getPlanId());

            //upDate the DCCaseEntity with plain id
            caseRepo.save(caseEntity1);//update Object operation
            return caseEntity1.getCaseNo();
        }
        return 0;
    }

    @Override
    public Integer saveIncomeDetails(IncomeInputs income) {
        //Convert binding obj to entity class object
        DcIncomeEntity incomeEntity = new DcIncomeEntity();
        BeanUtils.copyProperties(income,incomeEntity);

        //save the income details
        incomeRepository.save(incomeEntity);
        //return caseNumber
        return income.getCaseNo();

    }

    @Override
    public Integer saveEducationDetails(EducationInputs education) {
//        Covert Binding Object to Entity Object

        DcEducationEntity educationEntity = new DcEducationEntity();
        BeanUtils.copyProperties(education,educationEntity);
//        / save the obj
        educationRepository.save(educationEntity);
        // return case Number

        return education.getCaseNo();
    }

    @Override
    public Integer saveChildrenDetails(List<ChildInputs> children) {
        // Covert each binding class obj to each Entity class obj
        children.forEach(child->{
            DcChildrenEntity childEntity = new DcChildrenEntity();
            BeanUtils.copyProperties(child,childEntity);
            // save the entity object
            dcChildrenRepository.save(childEntity);

        });
        //return case number
        return children.get(0).getCaseNo();
    }

    @Override
    public DcSummaryReport showDcSummary(Integer caseNo) {
     // get multiple entity objs based
        DcIncomeEntity incomeEntity = incomeRepository.findByCaseNo(caseNo);
        DcEducationEntity educationEntity = educationRepository.findByCaseNo(caseNo);
        List<DcChildrenEntity>dcList = dcChildrenRepository.findByCaseNo(caseNo);
        Optional<DcCaseEntity> optCaseEntity = caseRepo.findById(caseNo);
        //get the planName
        String planName= null;
        Integer appId = null;

        if(optCaseEntity.isPresent()){
            DcCaseEntity caseEntity = new DcCaseEntity();
            Integer planId = caseEntity.getPlanId();
            appId =caseEntity.getAppId();
            Optional<PlanEntity> optionalPlanEntity=planRepository.findById(planId);
            if(optionalPlanEntity.isPresent()){
                planName= optionalPlanEntity.get().getPlanName();
            }
        }
       Optional<CitizenApplicationEntity>   optCitizenEntity =citizenAppRepo.findById(appId);
        CitizenApplicationEntity citizenEntity =null;

        //convert entity obj to binding object

        IncomeInputs income = new IncomeInputs();
        BeanUtils.copyProperties(incomeEntity,income);

        EducationInputs educationInputs = new EducationInputs();
        BeanUtils.copyProperties(educationEntity, educationInputs);

//        List<ChildInputs> listChild = new ArrayList<>();
//        for (DcChildrenEntity childEntity :  dcList) {
//            ChildInputs childInputs = new ChildInputs();
//            BeanUtils.copyProperties(childEntity, childInputs);
//            listChild.add(childInputs);
//        }
        List<ChildInputs> listChild= new ArrayList<>();
        dcList.forEach(childEntity->{
            ChildInputs childInputs = new ChildInputs();
            BeanUtils.copyProperties(childEntity,childInputs);
            listChild.add(childInputs);
        });
        CitizenAppRegistraionInputs citizen = new CitizenAppRegistraionInputs();
        BeanUtils.copyProperties(citizenEntity,citizen);

        // prepare dcSummary
        DcSummaryReport report = new DcSummaryReport();
        report.setPlanName(planName);
        report.setIncomeDetails(income);
        report.setEducationDetails(educationInputs);
        report.setCitizenDetails(citizen);
        report.setChildrenDetails(listChild);



        return report;
    }
}
