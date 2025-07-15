package com.wilfried.gestionproduits.mapper;

import com.wilfried.gestionproduits.dto.CategorieDTO;
import com.wilfried.gestionproduits.model.Categorie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategorieMapper {
    CategorieDTO toDTO(Categorie categorie);
    Categorie toEntity(CategorieDTO dto);
}
