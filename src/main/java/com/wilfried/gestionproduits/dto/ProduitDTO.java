package com.wilfried.gestionproduits.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProduitDTO {
    private Long id;
    private String nom;
    private double prix;
    private int quantite;
    private Long fournisseurId;
    private DetailProduitDTO detailProduit;
    private List<CategorieDTO> categories;
}