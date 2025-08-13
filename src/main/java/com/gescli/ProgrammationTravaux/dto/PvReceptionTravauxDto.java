package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PvReceptionTravauxDto {
    private Long idPvReception;
    private LocalDate datePv;
    private String observations;
    private String statutPv;
    private Long idTravail;
}