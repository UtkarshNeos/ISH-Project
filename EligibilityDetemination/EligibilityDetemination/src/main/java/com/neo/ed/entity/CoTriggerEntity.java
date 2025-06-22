package com.neo.ed.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CO_TRIGGERS")
public class CoTriggerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer triggerId;

    private Long caseNo;
    @Lob
    private byte[] coNoticePdf;
    @Column(length = 30)
    private  String  triggerStatus ="pending";

}
