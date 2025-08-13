package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.PVFinTravauxDto;
import com.gescli.ProgrammationTravaux.service.PvFinTravauxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pv-fin-travaux")
public class PvFinTravauxController {

    @Autowired
    private PvFinTravauxService pvFinTravauxService;

    @PostMapping
    public ResponseEntity<PVFinTravauxDto> createPVFinTravaux(@RequestBody PVFinTravauxDto pvFinTravauxDto) {
        PVFinTravauxDto newPV = pvFinTravauxService.createPVFinTravaux(pvFinTravauxDto);
        return new ResponseEntity<>(newPV, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PVFinTravauxDto> getPVFinTravauxById(@PathVariable Long id) {
        PVFinTravauxDto pv = pvFinTravauxService.getPVFinTravauxById(id);
        return new ResponseEntity<>(pv, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PVFinTravauxDto>> getAllPVFinTravaux() {
        List<PVFinTravauxDto> pvs = pvFinTravauxService.getAllPVFinTravaux();
        return new ResponseEntity<>(pvs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PVFinTravauxDto> updatePVFinTravaux(@PathVariable Long id, @RequestBody PVFinTravauxDto pvFinTravauxDto) {
        PVFinTravauxDto updatedPV = pvFinTravauxService.updatePVFinTravaux(id, pvFinTravauxDto);
        return new ResponseEntity<>(updatedPV, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePVFinTravaux(@PathVariable Long id) {
        pvFinTravauxService.deletePVFinTravaux(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}