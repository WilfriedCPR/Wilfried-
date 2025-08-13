package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.MaterielDto;
import com.gescli.ProgrammationTravaux.service.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/materiels")
public class MaterielController {

    @Autowired
    private MaterielService materielService;

    @PostMapping
    public ResponseEntity<MaterielDto> createMateriel(@RequestBody MaterielDto materielDto) {
        MaterielDto newMateriel = materielService.createMateriel(materielDto);
        return new ResponseEntity<>(newMateriel, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MaterielDto>> getAllMateriels() {
        List<MaterielDto> materiels = materielService.getAllMateriels();
        return new ResponseEntity<>(materiels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterielDto> getMaterielById(@PathVariable Long id) {
        MaterielDto materiel = materielService.getMaterielById(id);
        if (materiel != null) {
            return new ResponseEntity<>(materiel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterielDto> updateMateriel(@PathVariable Long id, @RequestBody MaterielDto materielDto) {
        MaterielDto updatedMateriel = materielService.updateMateriel(id, materielDto);
        if (updatedMateriel != null) {
            return new ResponseEntity<>(updatedMateriel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateriel(@PathVariable Long id) {
        materielService.deleteMateriel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}