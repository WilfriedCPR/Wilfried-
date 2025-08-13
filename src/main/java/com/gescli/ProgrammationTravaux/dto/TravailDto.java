package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravailDto {
    private Long idTravail;
    private String description;
    private LocalDate dateDebutPrevue;
    private LocalDate dateFinPrevue;
    private String statutTravaux;
    private String rapportExecution;
    private String typeBranchement;
    private LocalDateTime dateCreation;
    private LocalDateTime dateDerniereMaj;
    private Long planningTravauxId;
    private List<ContrainteDto> contraintes;
    private PVFinTravauxDto pvFinTravaux;
    private DemandeDeMaterielDto demandeMateriel;
    private FicheDeRenseignementDto ficheRenseignement;
    private BmsDto bms;
    private PvReceptionTravauxDto pvReceptionTravaux;
}