package com.neo.repository;

import com.neo.entity.DcEducationEntity;
import com.neo.entity.DcIncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DcEducationRepository  extends JpaRepository<DcEducationEntity,Integer> {
    public DcEducationEntity findByCaseNo(int caseNo);

}
