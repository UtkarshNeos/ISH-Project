package com.neo.bindings;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChildInputs {
    private Integer childId;
    private Integer caseNO;
    private LocalDate childDOB;
    private Long childSSN;

}
