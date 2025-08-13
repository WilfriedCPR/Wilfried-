package com.gescli.ProgrammationTravaux.repository;

import com.gescli.ProgrammationTravaux.model.EquipeTravaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeTravauxRepository extends JpaRepository<EquipeTravaux, Long> {
}
