package com.wilfried.gestionproduits.controller;

import com.wilfried.gestionproduits.dto.CategorieDTO;
import com.wilfried.gestionproduits.service.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;

    @PostMapping
    public ResponseEntity<CategorieDTO> createCategorie(@RequestBody CategorieDTO categorieDTO) {
        CategorieDTO savedCategorie = categorieService.createCategorie(categorieDTO);
        return new ResponseEntity<>(savedCategorie, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategorieDTO>> getAllCategories() {
        List<CategorieDTO> categories = categorieService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}
