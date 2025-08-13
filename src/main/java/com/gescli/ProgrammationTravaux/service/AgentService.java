package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.AgentDto;
import com.gescli.ProgrammationTravaux.mapper.AgentMapper;
import com.gescli.ProgrammationTravaux.model.EquipeTravaux;
import com.gescli.ProgrammationTravaux.repository.AgentRepository;
import com.gescli.ProgrammationTravaux.repository.EquipeTravauxRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private EquipeTravauxRepository equipeTravauxRepository;
    @Autowired
    private AgentMapper agentMapper;

    public AgentDto createAgent(AgentDto agentDto) {
        var agent = agentMapper.toEntity(agentDto);

        if (agentDto.getIdEquipe() != null) {
            EquipeTravaux equipe = equipeTravauxRepository.findById(agentDto.getIdEquipe())
                    .orElseThrow(() -> new EntityNotFoundException("Equipe de travaux non trouvée avec l'ID: " + agentDto.getIdEquipe()));
            agent.setEquipeTravaux(equipe);
        }

        var savedAgent = agentRepository.save(agent);
        return agentMapper.toDto(savedAgent);
    }

    public List<AgentDto> getAllAgents() {
        return agentRepository.findAll().stream()
                .map(agentMapper::toDto)
                .collect(Collectors.toList());
    }

    public AgentDto getAgentById(Long id) {
        var agent = agentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent non trouvé avec l'ID: " + id));
        return agentMapper.toDto(agent);
    }

    public AgentDto updateAgent(Long id, AgentDto agentDto) {
        var existingAgent = agentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agent non trouvé avec l'ID: " + id));

        existingAgent.setNom(agentDto.getNom());
        existingAgent.setPrenom(agentDto.getPrenom());
        existingAgent.setMatricule(agentDto.getMatricule());

        if (agentDto.getIdEquipe() != null) {
            EquipeTravaux nouvelleEquipe = equipeTravauxRepository.findById(agentDto.getIdEquipe())
                    .orElseThrow(() -> new EntityNotFoundException("Equipe de travaux non trouvée avec l'ID: " + agentDto.getIdEquipe()));
            existingAgent.setEquipeTravaux(nouvelleEquipe);
        } else {
            existingAgent.setEquipeTravaux(null);
        }

        var updatedAgent = agentRepository.save(existingAgent);
        return agentMapper.toDto(updatedAgent);
    }

    public void deleteAgent(Long id) {
        if (!agentRepository.existsById(id)) {
            throw new EntityNotFoundException("Agent non trouvé avec l'ID: " + id);
        }
        agentRepository.deleteById(id);
    }
}