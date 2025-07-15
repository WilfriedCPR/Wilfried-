package com.wilfried.gestionproduits.dto;

import lombok.Data;

@Data
public class DetailProduitDTO {
    private Long id;
    private String description;
    private String fabricant;
}