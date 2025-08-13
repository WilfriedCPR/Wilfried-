package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.PlanningTravauxDto;
import com.gescli.ProgrammationTravaux.service.PlanningTravauxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/plannings")
public class PlanningTravauxController {

    @Autowired
    private PlanningTravauxService planningTravauxService;

    @PostMapping
    public ResponseEntity<PlanningTravauxDto> createPlanning(@RequestBody PlanningTravauxDto planningTravauxDto) {
        PlanningTravauxDto createdPlanning = planningTravauxService.createPlanning(planningTravauxDto);
        return new ResponseEntity<>(createdPlanning, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanningTravauxDto> getPlanningById(@PathVariable Long id) {
        PlanningTravauxDto planning = planningTravauxService.getPlanningById(id);
        return ResponseEntity.ok(planning);
    }

    @GetMapping
    public ResponseEntity<List<PlanningTravauxDto>> getAllPlannings() {
        List<PlanningTravauxDto> plannings = planningTravauxService.getAllPlannings();
        return ResponseEntity.ok(plannings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanningTravauxDto> updatePlanning(@PathVariable Long id, @RequestBody PlanningTravauxDto planningTravauxDto) {
        PlanningTravauxDto updatedPlanning = planningTravauxService.updatePlanning(id, planningTravauxDto);
        return ResponseEntity.ok(updatedPlanning);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanning(@PathVariable Long id) {
        planningTravauxService.deletePlanning(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}