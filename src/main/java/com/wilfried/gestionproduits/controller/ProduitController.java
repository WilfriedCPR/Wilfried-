package com.wilfried.gestionproduits.controller;

import com.wilfried.gestionproduits.dto.ProduitDTO;
import com.wilfried.gestionproduits.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping
    public ResponseEntity<List<ProduitDTO>> getAll() {
        return ResponseEntity.ok(produitService.getAllProduits());
    }

    @PostMapping
    public ResponseEntity<ProduitDTO> create(@RequestBody ProduitDTO produitDTO) {
        ProduitDTO createdProduit = produitService.createProduit(produitDTO);
        return new ResponseEntity<>(createdProduit, HttpStatus.CREATED);
    }
}
