package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemandeDeMaterielDto {
    private Long idDemandeMateriel;
    private LocalDateTime dateDemande;
    private String statutDemande;
    private Long idTravail;
    private List<Long> materielIds;
}