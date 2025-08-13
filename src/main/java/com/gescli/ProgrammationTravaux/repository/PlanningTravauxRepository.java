package com.gescli.ProgrammationTravaux.repository;

import com.gescli.ProgrammationTravaux.model.PlanningTravaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningTravauxRepository extends JpaRepository<PlanningTravaux, Long> {
}