package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipeTravauxDto {
    private Long idEquipe;
    private String nomEquipe;
    private String localisation;
    private List<Long> agentIds;
}