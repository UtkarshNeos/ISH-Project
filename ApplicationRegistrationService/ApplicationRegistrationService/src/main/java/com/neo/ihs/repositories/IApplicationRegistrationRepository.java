package com.neo.ihs.repositories;

import com.neo.ihs.entities.CitizenApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IApplicationRegistrationRepository extends JpaRepository<CitizenApplicationEntity,Integer> {
}
