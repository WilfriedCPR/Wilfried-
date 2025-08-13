package com.gescli.ProgrammationTravaux.repository;

import com.gescli.ProgrammationTravaux.model.BonDeSortieMateriel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonDeSortieMaterielRepository extends JpaRepository<BonDeSortieMateriel, Long> {
    List<BonDeSortieMateriel> findByDemandeMaterielIdDemandeMateriel(Long idDemandeMateriel);
}