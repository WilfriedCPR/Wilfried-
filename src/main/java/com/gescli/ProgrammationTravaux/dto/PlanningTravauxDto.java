package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanningTravauxDto {
    private Long idPlanning;
    private String nomPlanning;
    private LocalDate dateDebutPrevue;
    private LocalDate dateFinPrevue;
    private String statut;
    private Long equipeTravauxId;
    private List<TravailDto> travaux;
}