package com.neo.service;

import com.neo.bindings.*;

import java.util.List;

public interface DcMgmtService {

    public Integer generateCaseNo(Integer appId);
    public List<String> showAllPlanNames();
    public Integer savePlanSelection(PlanSelectionInputs plan);
    public Integer saveIncomeDetails(IncomeInputs income);
    public Integer saveEducationDetails(EducationInputs education);
    public Integer saveChildrenDetails(List<ChildInputs> children);
    public DcSummaryReport showDcSummary(Integer caseNo);
}
