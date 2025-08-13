package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.DemandeDeMaterielDto;
import com.gescli.ProgrammationTravaux.service.DemandeDeMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/demandesmateriel")
public class DemandeDeMaterielController {

    @Autowired
    private DemandeDeMaterielService demandeDeMaterielService;

    @PostMapping
    public ResponseEntity<DemandeDeMaterielDto> createDemande(@RequestBody DemandeDeMaterielDto demandeDeMaterielDto) {
        DemandeDeMaterielDto createdDemande = demandeDeMaterielService.createDemande(demandeDeMaterielDto);
        return new ResponseEntity<>(createdDemande, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeDeMaterielDto> getDemandeById(@PathVariable Long id) {
        DemandeDeMaterielDto demande = demandeDeMaterielService.getDemandeById(id);
        return ResponseEntity.ok(demande);
    }

    @GetMapping
    public ResponseEntity<List<DemandeDeMaterielDto>> getAllDemandes() {
        List<DemandeDeMaterielDto> demandes = demandeDeMaterielService.getAllDemandes();
        return ResponseEntity.ok(demandes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeDeMaterielDto> updateDemande(@PathVariable Long id, @RequestBody DemandeDeMaterielDto demandeDeMaterielDto) {
        DemandeDeMaterielDto updatedDemande = demandeDeMaterielService.updateDemande(id, demandeDeMaterielDto);
        return ResponseEntity.ok(updatedDemande);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable Long id) {
        demandeDeMaterielService.deleteDemande(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}