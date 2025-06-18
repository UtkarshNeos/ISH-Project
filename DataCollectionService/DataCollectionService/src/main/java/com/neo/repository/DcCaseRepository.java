package com.neo.repository;

import com.neo.entity.DcCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DcCaseRepository extends JpaRepository<DcCaseEntity,Integer> {
    public Integer findByCaseNo(int caseNo);

}
