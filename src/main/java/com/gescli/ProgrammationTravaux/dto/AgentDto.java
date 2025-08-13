package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDto {
    private Long idAgent;
    private String nom;
    private String prenom;
    private String matricule;
    private Long idEquipe;
}
