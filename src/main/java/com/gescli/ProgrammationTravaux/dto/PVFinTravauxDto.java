package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PVFinTravauxDto {
    private Long idPV;
    private LocalDate datePv;
    private String statut;
    private Long idTravail;
}