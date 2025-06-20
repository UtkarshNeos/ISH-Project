package com.neo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "DC_CHILDREN")
public class DcChildrenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer childId;
    private Integer caseNo;
    private LocalDate childDOB;
    private Long childSSN;
}
