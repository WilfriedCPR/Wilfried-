package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.EquipeTravauxDto;
import com.gescli.ProgrammationTravaux.service.EquipeTravauxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipes")
public class EquipeTravauxController {

    @Autowired
    private EquipeTravauxService equipeTravauxService;

    @PostMapping
    public ResponseEntity<EquipeTravauxDto> createEquipe(@RequestBody EquipeTravauxDto equipeTravauxDto) {
        EquipeTravauxDto createdEquipe = equipeTravauxService.createEquipe(equipeTravauxDto);
        return new ResponseEntity<>(createdEquipe, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EquipeTravauxDto>> getAllEquipes() {
        List<EquipeTravauxDto> equipes = equipeTravauxService.getAllEquipes();
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeTravauxDto> getEquipeById(@PathVariable Long id) {
        EquipeTravauxDto equipe = equipeTravauxService.getEquipeById(id);
        return ResponseEntity.ok(equipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeTravauxDto> updateEquipe(@PathVariable Long id, @RequestBody EquipeTravauxDto equipeTravauxDto) {
        EquipeTravauxDto updatedEquipe = equipeTravauxService.updateEquipe(id, equipeTravauxDto);
        return ResponseEntity.ok(updatedEquipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipe(@PathVariable Long id) {
        equipeTravauxService.deleteEquipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}