package com.neo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.service.annotation.GetExchange;

@Entity
@Table(name="DC_INCOME")
@Data
public class DcIncomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer incomeId;
    private Integer caseNo;
    private Double empIncome;
    private Double propertyIncome;

}
