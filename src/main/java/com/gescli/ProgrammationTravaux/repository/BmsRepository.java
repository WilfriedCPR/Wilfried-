package com.gescli.ProgrammationTravaux.repository;

import com.gescli.ProgrammationTravaux.model.BMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BmsRepository extends JpaRepository<BMS, Long> {
}