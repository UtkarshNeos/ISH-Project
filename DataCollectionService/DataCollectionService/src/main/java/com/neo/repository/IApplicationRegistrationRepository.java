package com.neo.repository;

import com.neo.entity.CitizenApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IApplicationRegistrationRepository extends JpaRepository<CitizenApplicationEntity,Integer> {
}
