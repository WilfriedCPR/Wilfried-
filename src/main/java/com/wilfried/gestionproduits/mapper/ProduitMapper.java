package com.wilfried.gestionproduits.mapper;

import com.wilfried.gestionproduits.dto.ProduitDTO;
import com.wilfried.gestionproduits.model.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    @Mapping(source = "fournisseur.id", target = "fournisseurId")
    ProduitDTO produitToProduitDTO(Produit produit);

    @Mapping(source = "fournisseurId", target = "fournisseur.id")
    Produit produitDTOToProduit(ProduitDTO dto);
}