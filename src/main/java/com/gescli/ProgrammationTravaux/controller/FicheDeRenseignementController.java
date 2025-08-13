package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.FicheDeRenseignementDto;
import com.gescli.ProgrammationTravaux.service.FicheDeRenseignementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fiches-renseignement")
public class FicheDeRenseignementController {

    @Autowired
    private FicheDeRenseignementService ficheDeRenseignementService;

    @PostMapping
    public ResponseEntity<FicheDeRenseignementDto> createFiche(@RequestBody FicheDeRenseignementDto ficheDeRenseignementDto) {
        FicheDeRenseignementDto newFiche = ficheDeRenseignementService.createFiche(ficheDeRenseignementDto);
        return new ResponseEntity<>(newFiche, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FicheDeRenseignementDto> getFicheById(@PathVariable Long id) {
        FicheDeRenseignementDto fiche = ficheDeRenseignementService.getFicheById(id);
        return new ResponseEntity<>(fiche, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FicheDeRenseignementDto>> getAllFiches() {
        List<FicheDeRenseignementDto> fiches = ficheDeRenseignementService.getAllFiches();
        return new ResponseEntity<>(fiches, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FicheDeRenseignementDto> updateFiche(@PathVariable Long id, @RequestBody FicheDeRenseignementDto ficheDeRenseignementDto) {
        FicheDeRenseignementDto updatedFiche = ficheDeRenseignementService.updateFiche(id, ficheDeRenseignementDto);
        return new ResponseEntity<>(updatedFiche, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiche(@PathVariable Long id) {
        ficheDeRenseignementService.deleteFiche(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}