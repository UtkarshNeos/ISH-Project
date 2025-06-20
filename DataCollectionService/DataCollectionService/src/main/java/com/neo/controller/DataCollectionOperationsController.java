package com.neo.controller;

import com.neo.bindings.*;
import com.neo.service.DcMgmtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dc-api")
@Tag(name = "Data Collection API", description = "Data Collection module microservice")
public class DataCollectionOperationsController {

    @Autowired
    private DcMgmtService dcMgmtService;

    @Operation(summary = "Get all plan names", description = "Fetches all available plan names")
    @GetMapping("/planNames")
    public ResponseEntity<List<String>> getAllPlanNames() {
        List<String> listPlanNames = dcMgmtService.showAllPlanNames();
        return ResponseEntity.ok(listPlanNames);
    }

    @Operation(summary = "Generate case number", description = "Generates a unique case number for given App ID")
    @PostMapping("/generateCaseNo/{appId}")
    public ResponseEntity<Integer> generateCaseNo(@PathVariable Integer appId) {
        Integer caseNo = dcMgmtService.generateCaseNo(appId);
        return new ResponseEntity<>(caseNo, HttpStatus.OK);
    }

    @Operation(summary = "Save plan selection", description = "Saves selected plan for a given case")
    @PutMapping("/updatePlanSelection")
    public ResponseEntity<Integer> savePlanSelection(@RequestBody PlanSelectionInputs inputs) {
        Integer caseNo = dcMgmtService.savePlanSelection(inputs);
        return new ResponseEntity<>(caseNo, HttpStatus.OK);
    }

    @Operation(summary = "Save income details", description = "Stores income details for a case")
    @PostMapping("/saveIncome")
    public ResponseEntity<Integer> saveIncomeDetails(@RequestBody IncomeInputs incomeInputs) {
        Integer caseNo = dcMgmtService.saveIncomeDetails(incomeInputs);
        return new ResponseEntity<>(caseNo, HttpStatus.CREATED);
    }

    @Operation(summary = "Save education details", description = "Stores education details for a case")
    @PostMapping("/saveEducation")
    public ResponseEntity<Integer> saveEducationDetails(@RequestBody EducationInputs educationInputs) {
        Integer caseNo = dcMgmtService.saveEducationDetails(educationInputs);
        return new ResponseEntity<>(caseNo, HttpStatus.CREATED);
    }

    @Operation(summary = "Save children details", description = "Stores children details for a case")
    @PostMapping("/saveChildrenDetails")
    public ResponseEntity<Integer> saveChildrenDetails(@RequestBody List<ChildInputs> childInputs) {
        Integer caseNo = dcMgmtService.saveChildrenDetails(childInputs);
        return new ResponseEntity<>(caseNo, HttpStatus.OK);
    }

    @Operation(summary = "Get case summary", description = "Returns a summary of all DC details for a given case number")
    @GetMapping("/summary/{caseNo}")
    public ResponseEntity<DcSummaryReport> showDcSummary(@PathVariable Integer caseNo) {
        DcSummaryReport dcSummaryReport = dcMgmtService.showDcSummary(caseNo);
        return new ResponseEntity<>(dcSummaryReport, HttpStatus.OK);
    }
}
