package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.BonDeSortieMaterielDto;
import com.gescli.ProgrammationTravaux.service.BonDeSortieMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bons-de-sortie-materiel")
public class BonDeSortieMaterielController {

    @Autowired
    private BonDeSortieMaterielService bonDeSortieMaterielService;

    @PostMapping
    public ResponseEntity<BonDeSortieMaterielDto> createBonDeSortie(@RequestBody BonDeSortieMaterielDto bonDeSortieMaterielDto) {
        BonDeSortieMaterielDto newBonDeSortie = bonDeSortieMaterielService.createBonDeSortie(bonDeSortieMaterielDto);
        return new ResponseEntity<>(newBonDeSortie, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonDeSortieMaterielDto> getBonDeSortieById(@PathVariable Long id) {
        BonDeSortieMaterielDto bonDeSortie = bonDeSortieMaterielService.getBonDeSortieById(id);
        return new ResponseEntity<>(bonDeSortie, HttpStatus.OK);
    }

    @GetMapping("/demande/{idDemande}")
    public ResponseEntity<List<BonDeSortieMaterielDto>> getBonsDeSortieByDemandeId(@PathVariable Long idDemande) {
        List<BonDeSortieMaterielDto> bons = bonDeSortieMaterielService.getBonsDeSortieByDemandeId(idDemande);
        return new ResponseEntity<>(bons, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BonDeSortieMaterielDto> updateBonDeSortie(@PathVariable Long id, @RequestBody BonDeSortieMaterielDto bonDeSortieMaterielDto) {
        BonDeSortieMaterielDto updatedBon = bonDeSortieMaterielService.updateBonDeSortie(id, bonDeSortieMaterielDto);
        return new ResponseEntity<>(updatedBon, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonDeSortie(@PathVariable Long id) {
        bonDeSortieMaterielService.deleteBonDeSortie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}