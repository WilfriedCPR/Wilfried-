package com.gescli.ProgrammationTravaux.repository;

import com.gescli.ProgrammationTravaux.model.PVFinTravaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PVFinTravauxRepository extends JpaRepository<PVFinTravaux, Long> {
}
