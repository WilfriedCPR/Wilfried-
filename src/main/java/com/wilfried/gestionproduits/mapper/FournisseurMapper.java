package com.wilfried.gestionproduits.mapper;

import com.wilfried.gestionproduits.dto.FournisseurDTO;
import com.wilfried.gestionproduits.model.Fournisseur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FournisseurMapper {
    FournisseurDTO toDto(Fournisseur fournisseur);
    Fournisseur toEntity(FournisseurDTO dto);
}
