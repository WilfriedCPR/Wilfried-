package com.wilfried.gestionproduits.mapper;

import com.wilfried.gestionproduits.dto.ProduitDTO;
import com.wilfried.gestionproduits.model.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FournisseurMapper.class, CategorieMapper.class})
public interface ProduitMapper {
    
    @Mapping(source = "fournisseur.id", target = "fournisseurId")
    @Mapping(source = "fournisseur", target = "fournisseur")
    ProduitDTO toDto(Produit produit);
    
    @Mapping(target = "fournisseur", ignore = true)
    Produit toEntity(ProduitDTO dto);
}
