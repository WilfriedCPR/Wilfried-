package com.gescli.ProgrammationTravaux.repository;

import com.gescli.ProgrammationTravaux.model.PVReceptionTravaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PvReceptionTravauxRepository extends JpaRepository<PVReceptionTravaux, Long> {
}