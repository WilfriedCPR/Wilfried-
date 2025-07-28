package com.wilfried.gestionproduits.dto;

import java.util.Set;

public record ProduitDTO(
    Long id,
    String nom,
    double prix,
    Long fournisseurId,
    FournisseurDTO fournisseur,
    DetailProduitDTO detailProduit,
    Set<CategorieDTO> categories
) {}
