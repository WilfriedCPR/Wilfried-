package com.gescli.ProgrammationTravaux.repository;

import com.gescli.ProgrammationTravaux.model.Contrainte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContrainteRepository extends JpaRepository<Contrainte, Long> {
    List<Contrainte> findByTravailIdTravail(Long idTravail);
}