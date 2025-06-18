package com.neo.repository;

import com.neo.entity.DcChildrenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DcChildrenRepository extends JpaRepository<DcChildrenEntity,Integer> {
    public List<DcChildrenEntity> findByCaseNo(int caseNo);
}
