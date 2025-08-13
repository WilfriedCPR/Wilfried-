package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FicheDeRenseignementDto {
    private Long idFiche;
    private LocalDate dateCreation;
    private String observationsTerrain;
    private Long idTravail;
}