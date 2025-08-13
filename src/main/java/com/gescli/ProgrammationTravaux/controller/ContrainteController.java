package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.ContrainteDto;
import com.gescli.ProgrammationTravaux.service.ContrainteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contraintes")
public class ContrainteController {

    @Autowired
    private ContrainteService contrainteService;

    @PostMapping
    public ResponseEntity<ContrainteDto> createContrainte(@RequestBody ContrainteDto contrainteDto) {
        ContrainteDto newContrainte = contrainteService.createContrainte(contrainteDto);
        return new ResponseEntity<>(newContrainte, HttpStatus.CREATED);
    }

    @GetMapping("/travail/{idTravail}")
    public ResponseEntity<List<ContrainteDto>> getContraintesByTravailId(@PathVariable Long idTravail) {
        List<ContrainteDto> contraintes = contrainteService.getContraintesByTravailId(idTravail);
        return new ResponseEntity<>(contraintes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContrainteDto> updateContrainte(@PathVariable Long id, @RequestBody ContrainteDto contrainteDto) {
        ContrainteDto updatedContrainte = contrainteService.updateContrainte(id, contrainteDto);
        if (updatedContrainte != null) {
            return new ResponseEntity<>(updatedContrainte, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrainte(@PathVariable Long id) {
        contrainteService.deleteContrainte(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}