package com.neo.ed.binding;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EligibilityDetailsOutput {

    private  String holderName;


    private  String planName;

    private  String planStatus;

    private LocalDateTime planStartDate;

    private  LocalDateTime planEndDate;
    private  Double benifitAmt;

    private  String denialReason;

}
