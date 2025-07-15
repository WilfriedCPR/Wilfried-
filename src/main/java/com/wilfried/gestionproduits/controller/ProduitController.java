package com.wilfried.gestionproduits.controller;

import com.wilfried.gestionproduits.dto.ProduitDTO;
import com.wilfried.gestionproduits.mapper.CategorieMapper;
import com.wilfried.gestionproduits.mapper.DetailProduitMapper;
import com.wilfried.gestionproduits.mapper.ProduitMapper;
import com.wilfried.gestionproduits.model.Categorie;
import com.wilfried.gestionproduits.model.DetailProduit;
import com.wilfried.gestionproduits.model.Fournisseur;
import com.wilfried.gestionproduits.model.Produit;
import com.wilfried.gestionproduits.repository.CategorieRepository;
import com.wilfried.gestionproduits.repository.DetailProduitRepository;
import com.wilfried.gestionproduits.repository.FournisseurRepository;
import com.wilfried.gestionproduits.repository.ProduitRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitRepository produitRepository;
    private final FournisseurRepository fournisseurRepository;
    private final DetailProduitRepository detailRepository;
    private final CategorieRepository categorieRepository;
    private final ProduitMapper produitMapper;
    private final DetailProduitMapper detailMapper;
    private final CategorieMapper categorieMapper;

    public ProduitController(ProduitRepository produitRepository,
                             FournisseurRepository fournisseurRepository,
                             DetailProduitRepository detailRepository,
                             CategorieRepository categorieRepository,
                             ProduitMapper produitMapper,
                             DetailProduitMapper detailMapper,
                             CategorieMapper categorieMapper) {
        this.produitRepository = produitRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.detailRepository = detailRepository;
        this.categorieRepository = categorieRepository;
        this.produitMapper = produitMapper;
        this.detailMapper = detailMapper;
        this.categorieMapper = categorieMapper;
    }

    @GetMapping
    public List<ProduitDTO> getAll() {
        return produitRepository.findAll()
                .stream()
                .map(produitMapper::produitToProduitDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProduitDTO create(@RequestBody ProduitDTO dto) {
        Produit produit = produitMapper.produitDTOToProduit(dto);

        Fournisseur fournisseur = fournisseurRepository.findById(dto.getFournisseurId())
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));
        produit.setFournisseur(fournisseur);

        if (dto.getDetailProduit() != null) {
            DetailProduit detail = detailMapper.toEntity(dto.getDetailProduit());
            detail = detailRepository.save(detail);
            produit.setDetailProduit(detail);
        }

        if (dto.getCategories() != null) {
            List<Categorie> categories = dto.getCategories().stream()
                    .map(c -> categorieRepository.findById(c.getId())
                            .orElseThrow(() -> new RuntimeException("Categorie introuvable : " + c.getId())))
                    .collect(Collectors.toList());
            produit.setCategories(categories);
        }

        Produit saved = produitRepository.save(produit);
        return produitMapper.produitToProduitDTO(saved);
    }

    @PutMapping("/{id}")
    public ProduitDTO update(@PathVariable Long id, @RequestBody ProduitDTO dto) {
        Produit existing = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        existing.setNom(dto.getNom());
        existing.setPrix(dto.getPrix());
        existing.setQuantite(dto.getQuantite());

        Fournisseur fournisseur = fournisseurRepository.findById(dto.getFournisseurId())
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));
        existing.setFournisseur(fournisseur);

        if (dto.getDetailProduit() != null) {
            DetailProduit detail = detailMapper.toEntity(dto.getDetailProduit());
            detail = detailRepository.save(detail);
            existing.setDetailProduit(detail);
        }

        if (dto.getCategories() != null) {
            List<Categorie> categories = dto.getCategories().stream()
                    .map(c -> categorieRepository.findById(c.getId())
                            .orElseThrow(() -> new RuntimeException("Catégorie introuvable : " + c.getId())))
                    .collect(Collectors.toList());
            existing.setCategories(categories);
        }

        Produit updated = produitRepository.save(existing);
        return produitMapper.produitToProduitDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        produitRepository.delete(produit);
    }
}
