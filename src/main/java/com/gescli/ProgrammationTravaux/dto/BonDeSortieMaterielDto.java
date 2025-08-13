package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonDeSortieMaterielDto {
    private Long idBonSortie;
    private LocalDate dateEmission;
    private Integer quantiteDemandee;
    private String statut;
    private String signature;
    private Long idDemandeMateriel;
}
