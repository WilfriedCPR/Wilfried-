package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.BmsDto;
import com.gescli.ProgrammationTravaux.service.BmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bms")
public class BmsController {

    @Autowired
    private BmsService bmsService;

    @PostMapping
    public ResponseEntity<BmsDto> createBms(@RequestBody BmsDto bmsDto) {
        BmsDto newBms = bmsService.createBms(bmsDto);
        return new ResponseEntity<>(newBms, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BmsDto> getBmsById(@PathVariable Long id) {
        BmsDto bms = bmsService.getBmsById(id);
        return new ResponseEntity<>(bms, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BmsDto>> getAllBms() {
        List<BmsDto> bmsList = bmsService.getAllBms();
        return new ResponseEntity<>(bmsList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BmsDto> updateBms(@PathVariable Long id, @RequestBody BmsDto bmsDto) {
        BmsDto updatedBms = bmsService.updateBms(id, bmsDto);
        return new ResponseEntity<>(updatedBms, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBms(@PathVariable Long id) {
        bmsService.deleteBms(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}