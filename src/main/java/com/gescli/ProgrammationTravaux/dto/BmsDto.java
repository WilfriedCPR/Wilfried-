package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BmsDto {
    private Long idBMS;
    private LocalDate dateEmission;
    private LocalDate dateMiseEnService;
    private Long idTravail;
}
