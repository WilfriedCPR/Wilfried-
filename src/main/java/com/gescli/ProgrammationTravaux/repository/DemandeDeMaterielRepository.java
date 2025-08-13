package com.gescli.ProgrammationTravaux.repository;

import com.gescli.ProgrammationTravaux.model.DemandeDeMateriel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeDeMaterielRepository extends JpaRepository<DemandeDeMateriel, Long> {
}
