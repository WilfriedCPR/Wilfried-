package com.wilfried.gestionproduits.mapper;

import org.mapstruct.Mapper;
import com.wilfried.gestionproduits.dto.CategorieDTO;
import com.wilfried.gestionproduits.model.Categorie;

@Mapper(componentModel = "spring")
public interface CategorieMapper {
    CategorieDTO toDto(Categorie categorie);
    Categorie toEntity(CategorieDTO categorieDto);
}