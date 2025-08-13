package com.gescli.ProgrammationTravaux.repository;

import com.gescli.ProgrammationTravaux.model.FicheDeRenseignement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FicheDeRenseignementRepository extends JpaRepository<FicheDeRenseignement, Long> {
}
