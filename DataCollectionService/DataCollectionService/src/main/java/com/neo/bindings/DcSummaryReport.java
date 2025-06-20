package com.neo.bindings;

import lombok.Data;

import java.util.List;

@Data
public class DcSummaryReport {
    private  EducationInputs educationDetails;
    private List<ChildInputs> childrenDetails;
    private IncomeInputs incomeDetails;
    private  CitizenAppRegistraionInputs citizenDetails;
    private  String planName;
}
