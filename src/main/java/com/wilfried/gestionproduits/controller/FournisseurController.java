package com.wilfried.gestionproduits.controller;

import com.wilfried.gestionproduits.dto.FournisseurDTO;
import com.wilfried.gestionproduits.service.FournisseurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
@RequiredArgsConstructor
public class FournisseurController {

    private final FournisseurService fournisseurService;

    @PostMapping
    public ResponseEntity<FournisseurDTO> createFournisseur(@RequestBody FournisseurDTO fournisseurDTO) {
        FournisseurDTO savedFournisseur = fournisseurService.createFournisseur(fournisseurDTO);
        return new ResponseEntity<>(savedFournisseur, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FournisseurDTO>> getAllFournisseurs() {
        List<FournisseurDTO> fournisseurs = fournisseurService.getAllFournisseurs();
        return ResponseEntity.ok(fournisseurs);
    }
}
