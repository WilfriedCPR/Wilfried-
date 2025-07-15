package com.wilfried.gestionproduits.controller;

import com.wilfried.gestionproduits.dto.DetailProduitDTO;
import com.wilfried.gestionproduits.mapper.DetailProduitMapper;
import com.wilfried.gestionproduits.model.DetailProduit;
import com.wilfried.gestionproduits.repository.DetailProduitRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/details")
public class DetailProduitController {

    private final DetailProduitRepository detailRepository;
    private final DetailProduitMapper detailMapper;

    public DetailProduitController(DetailProduitRepository detailRepository, DetailProduitMapper detailMapper) {
        this.detailRepository = detailRepository;
        this.detailMapper = detailMapper;
    }

    @GetMapping
    public List<DetailProduitDTO> getAll() {
        return detailRepository.findAll()
                .stream()
                .map(detailMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public DetailProduitDTO create(@RequestBody DetailProduitDTO dto) {
        DetailProduit saved = detailRepository.save(detailMapper.toEntity(dto));
        return detailMapper.toDTO(saved);
    }
}
