package com.gescli.ProgrammationTravaux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterielDto {
    private Long idMateriel;
    private String nomMateriel;
    private String description;
    private Integer quantiteEnStock;
}