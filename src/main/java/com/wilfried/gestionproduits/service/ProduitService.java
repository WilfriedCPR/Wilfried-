package com.wilfried.gestionproduits.service;

import com.wilfried.gestionproduits.dto.ProduitDTO;
import com.wilfried.gestionproduits.mapper.ProduitMapper;
import com.wilfried.gestionproduits.model.Fournisseur;
import com.wilfried.gestionproduits.model.Produit;
import com.wilfried.gestionproduits.repository.FournisseurRepository;
import com.wilfried.gestionproduits.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final FournisseurRepository fournisseurRepository;
    private final ProduitMapper produitMapper;

    @Transactional(readOnly = true)
    public List<ProduitDTO> getAllProduits() {
        return produitRepository.findAll().stream()
                .map(produitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProduitDTO createProduit(ProduitDTO produitDTO) {
        Fournisseur fournisseur = fournisseurRepository.findById(produitDTO.fournisseurId())
                .orElseThrow(() -> new RuntimeException("Fournisseur introuvable avec l'ID : " + produitDTO.fournisseurId()));

        Produit produit = produitMapper.toEntity(produitDTO);
        produit.setFournisseur(fournisseur);
        if (produit.getDetailProduit() != null) {
            produit.getDetailProduit().setProduit(produit);
        }

        Produit savedProduit = produitRepository.save(produit);
        return produitMapper.toDto(savedProduit);
    }
}
