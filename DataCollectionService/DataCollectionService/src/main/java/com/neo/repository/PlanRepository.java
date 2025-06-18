package com.neo.repository;

import com.neo.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<PlanEntity,Integer> {
}
