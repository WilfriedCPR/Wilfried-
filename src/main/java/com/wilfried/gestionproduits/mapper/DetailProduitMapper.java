package com.wilfried.gestionproduits.mapper;

import com.wilfried.gestionproduits.dto.DetailProduitDTO;
import com.wilfried.gestionproduits.model.DetailProduit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetailProduitMapper {
    DetailProduitDTO toDTO(DetailProduit detailProduit);
    DetailProduit toEntity(DetailProduitDTO dto);
}