package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContrainteDto {
    private Long idContrainte;
    private String description;
    private String statut;
    private Long idTravail;
}