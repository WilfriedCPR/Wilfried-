package com.wilfried.gestionproduits.service;

import com.wilfried.gestionproduits.dto.FournisseurDTO;
import com.wilfried.gestionproduits.mapper.FournisseurMapper;
import com.wilfried.gestionproduits.model.Fournisseur;
import com.wilfried.gestionproduits.repository.FournisseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private final FournisseurMapper fournisseurMapper;

    @Transactional
    public FournisseurDTO createFournisseur(FournisseurDTO fournisseurDTO) {
        Fournisseur fournisseur = fournisseurMapper.toEntity(fournisseurDTO);
        Fournisseur savedFournisseur = fournisseurRepository.save(fournisseur);
        return fournisseurMapper.toDto(savedFournisseur);
    }

    @Transactional(readOnly = true)
    public List<FournisseurDTO> getAllFournisseurs() {
        return fournisseurRepository.findAll()
                .stream()
                .map(fournisseurMapper::toDto)
                .collect(Collectors.toList());
    }
}
