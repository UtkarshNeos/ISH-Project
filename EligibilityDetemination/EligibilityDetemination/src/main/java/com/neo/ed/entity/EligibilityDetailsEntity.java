package com.neo.ed.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ELIGIBILITY_DETERMINATION")
public class EligibilityDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer edTraceId;

    private Integer caseNo;

    @Column(length = 30)
    private  String holderName;

    @Column(length = 30)
    private String holderSSN;

    @Column(length = 30)
    private  String planName;

    @Column(length = 30)
    private  String planStatus;

    private LocalDateTime planStartDate;

    private  LocalDateTime planEndDate;
    private  Double benifitAmt;

    @Column(length = 30)
    private  String denialReason;

}
