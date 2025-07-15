package com.wilfried.gestionproduits.controller;

import com.wilfried.gestionproduits.dto.FournisseurDTO;
import com.wilfried.gestionproduits.mapper.FournisseurMapper;
import com.wilfried.gestionproduits.model.Fournisseur;
import com.wilfried.gestionproduits.repository.FournisseurRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fournisseurs")
public class FournisseurController {

    private final FournisseurRepository fournisseurRepository;
    private final FournisseurMapper fournisseurMapper;

    public FournisseurController(FournisseurRepository fournisseurRepository, FournisseurMapper fournisseurMapper) {
        this.fournisseurRepository = fournisseurRepository;
        this.fournisseurMapper = fournisseurMapper;
    }

    @GetMapping
    public List<FournisseurDTO> getAll() {
        return fournisseurRepository.findAll()
                .stream()
                .map(fournisseurMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public FournisseurDTO create(@RequestBody FournisseurDTO dto) {
        Fournisseur saved = fournisseurRepository.save(fournisseurMapper.toEntity(dto));
        return fournisseurMapper.toDTO(saved);
    }
}