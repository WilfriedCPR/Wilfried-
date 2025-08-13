package com.gescli.ProgrammationTravaux.controller;

import com.gescli.ProgrammationTravaux.dto.AgentDto;
import com.gescli.ProgrammationTravaux.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping
    public ResponseEntity<AgentDto> createAgent(@RequestBody AgentDto agentDto) {
        AgentDto createdAgent = agentService.createAgent(agentDto);
        return new ResponseEntity<>(createdAgent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AgentDto>> getAllAgents() {
        List<AgentDto> agents = agentService.getAllAgents();
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgentDto> getAgentById(@PathVariable Long id) {
        AgentDto agent = agentService.getAgentById(id);
        return ResponseEntity.ok(agent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgentDto> updateAgent(@PathVariable Long id, @RequestBody AgentDto agentDto) {
        AgentDto updatedAgent = agentService.updateAgent(id, agentDto);
        return ResponseEntity.ok(updatedAgent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
        return ResponseEntity.noContent().build();
    }
}