package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.TravailDto;
import com.gescli.ProgrammationTravaux.service.TravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/travaux")
public class TravailController {

    @Autowired
    private TravailService travailService;

    @PostMapping
    public ResponseEntity<TravailDto> createTravail(@RequestBody TravailDto travailDto) {
        TravailDto createdTravail = travailService.createTravail(travailDto);
        return new ResponseEntity<>(createdTravail, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TravailDto>> getAllTravaux() {
        List<TravailDto> travaux = travailService.getAllTravaux();
        return ResponseEntity.ok(travaux);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravailDto> getTravailById(@PathVariable Long id) {
        TravailDto travail = travailService.getTravailById(id);
        return ResponseEntity.ok(travail);
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<TravailDto>> getTravauxByStatut(@PathVariable String statut) {
        List<TravailDto> travaux = travailService.getTravauxByStatut(statut);
        return ResponseEntity.ok(travaux);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravailDto> updateTravail(@PathVariable Long id, @RequestBody TravailDto travailDto) {
        TravailDto updatedTravail = travailService.updateTravail(id, travailDto);
        return ResponseEntity.ok(updatedTravail);
    }

    @PutMapping("/{id}/statut")
    public ResponseEntity<TravailDto> updateStatutTravail(@PathVariable Long id, @RequestParam String nouveauStatut) {
        TravailDto updatedTravail = travailService.updateStatutTravail(id, nouveauStatut);
        return ResponseEntity.ok(updatedTravail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravail(@PathVariable Long id) {
        travailService.deleteTravail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}