package com.wilfried.gestionproduits.service;

import com.wilfried.gestionproduits.dto.CategorieDTO;
import com.wilfried.gestionproduits.mapper.CategorieMapper;
import com.wilfried.gestionproduits.model.Categorie;
import com.wilfried.gestionproduits.repository.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorieService {

    private final CategorieRepository categorieRepository;
    private final CategorieMapper categorieMapper;

    @Transactional
    public CategorieDTO createCategorie(CategorieDTO categorieDTO) {
        Categorie categorie = categorieMapper.toEntity(categorieDTO);
        Categorie savedCategorie = categorieRepository.save(categorie);
        return categorieMapper.toDto(savedCategorie);
    }

    @Transactional(readOnly = true)
    public List<CategorieDTO> getAllCategories() {
        return categorieRepository.findAll()
                .stream()
                .map(categorieMapper::toDto)
                .collect(Collectors.toList());
    }
}
