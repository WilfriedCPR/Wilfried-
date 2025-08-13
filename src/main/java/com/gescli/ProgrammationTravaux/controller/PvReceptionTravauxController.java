package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.PvReceptionTravauxDto;
import com.gescli.ProgrammationTravaux.service.PvReceptionTravauxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pv-reception-travaux")
public class PvReceptionTravauxController {

    @Autowired
    private PvReceptionTravauxService pvReceptionTravauxService;

    @PostMapping
    public ResponseEntity<PvReceptionTravauxDto> createPvReceptionTravaux(@RequestBody PvReceptionTravauxDto pvReceptionTravauxDto) {
        PvReceptionTravauxDto newPV = pvReceptionTravauxService.createPvReceptionTravaux(pvReceptionTravauxDto);
        return new ResponseEntity<>(newPV, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PvReceptionTravauxDto> getPvReceptionTravauxById(@PathVariable Long id) {
        PvReceptionTravauxDto pv = pvReceptionTravauxService.getPvReceptionTravauxById(id);
        return new ResponseEntity<>(pv, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PvReceptionTravauxDto>> getAllPvReceptionTravaux() {
        List<PvReceptionTravauxDto> pvs = pvReceptionTravauxService.getAllPvReceptionTravaux();
        return new ResponseEntity<>(pvs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PvReceptionTravauxDto> updatePvReceptionTravaux(@PathVariable Long id, @RequestBody PvReceptionTravauxDto pvReceptionTravauxDto) {
        PvReceptionTravauxDto updatedPV = pvReceptionTravauxService.updatePvReceptionTravaux(id, pvReceptionTravauxDto);
        return new ResponseEntity<>(updatedPV, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePvReceptionTravaux(@PathVariable Long id) {
        pvReceptionTravauxService.deletePvReceptionTravaux(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}