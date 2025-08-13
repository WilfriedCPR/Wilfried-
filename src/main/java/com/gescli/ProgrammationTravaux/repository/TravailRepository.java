package com.gescli.ProgrammationTravaux.repository;

import com.gescli.ProgrammationTravaux.model.Travail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravailRepository extends JpaRepository<Travail, Long> {
    List<Travail> findByPlanningTravauxIdPlanning(Long idPlanning);
    List<Travail> findByStatutTravaux(String statut);
}
