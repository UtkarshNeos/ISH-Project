package com.neo.repository;

import com.neo.entity.DcIncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DcIncomeRepository extends JpaRepository<DcIncomeEntity,Integer > {
    public DcIncomeEntity findByCaseNo(int caseNo);
}
