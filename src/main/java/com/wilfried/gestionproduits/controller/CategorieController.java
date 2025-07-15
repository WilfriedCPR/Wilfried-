package com.wilfried.gestionproduits.controller;

import com.wilfried.gestionproduits.dto.CategorieDTO;
import com.wilfried.gestionproduits.mapper.CategorieMapper;
import com.wilfried.gestionproduits.model.Categorie;
import com.wilfried.gestionproduits.repository.CategorieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    private final CategorieRepository categorieRepository;
    private final CategorieMapper categorieMapper;

    public CategorieController(CategorieRepository categorieRepository, CategorieMapper categorieMapper) {
        this.categorieRepository = categorieRepository;
        this.categorieMapper = categorieMapper;
    }

    @GetMapping
    public List<CategorieDTO> getAll() {
        return categorieRepository.findAll()
                .stream()
                .map(categorieMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CategorieDTO create(@RequestBody CategorieDTO dto) {
        Categorie saved = categorieRepository.save(categorieMapper.toEntity(dto));
        return categorieMapper.toDTO(saved);
    }
}