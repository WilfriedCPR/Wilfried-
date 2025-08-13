package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.EquipeTravauxDto;
import com.gescli.ProgrammationTravaux.mapper.EquipeTravauxMapper;
import com.gescli.ProgrammationTravaux.model.Agent;
import com.gescli.ProgrammationTravaux.model.EquipeTravaux;
import com.gescli.ProgrammationTravaux.repository.AgentRepository;
import com.gescli.ProgrammationTravaux.repository.EquipeTravauxRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;

@Service
public class EquipeTravauxService {

    @Autowired
    private EquipeTravauxRepository equipeTravauxRepository;
    @Autowired
    private EquipeTravauxMapper equipeTravauxMapper;
    @Autowired
    private AgentRepository agentRepository;

    public EquipeTravauxDto createEquipe(EquipeTravauxDto equipeTravauxDto) {
        var equipe = equipeTravauxMapper.toEntity(equipeTravauxDto);

        if (equipeTravauxDto.getAgentIds() != null && !equipeTravauxDto.getAgentIds().isEmpty()) {
            Set<Agent> agents = equipeTravauxDto.getAgentIds().stream()
                .map(id -> agentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Agent non trouvé avec l'ID: " + id)))
                .collect(Collectors.toSet());
            equipe.setAgents(agents);
            agents.forEach(agent -> agent.setEquipeTravaux(equipe));
        }

        var savedEquipe = equipeTravauxRepository.save(equipe);
        return equipeTravauxMapper.toDto(savedEquipe);
    }

    public EquipeTravauxDto getEquipeById(Long id) {
        var equipe = equipeTravauxRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipe non trouvée avec l'ID: " + id));
        return equipeTravauxMapper.toDto(equipe);
    }

    public List<EquipeTravauxDto> getAllEquipes() {
        return equipeTravauxRepository.findAll().stream()
                .map(equipeTravauxMapper::toDto)
                .collect(Collectors.toList());
    }

    public EquipeTravauxDto updateEquipe(Long id, EquipeTravauxDto equipeTravauxDto) {
        EquipeTravaux existingEquipe = equipeTravauxRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipe non trouvée avec l'ID: " + id));

        existingEquipe.setNomEquipe(equipeTravauxDto.getNomEquipe());
        existingEquipe.setLocalisation(equipeTravauxDto.getLocalisation());

        if (equipeTravauxDto.getAgentIds() != null) {
            Set<Agent> nouveauxAgents = equipeTravauxDto.getAgentIds().stream()
                    .map(agentId -> agentRepository.findById(agentId)
                            .orElseThrow(() -> new EntityNotFoundException("Agent non trouvé avec l'ID: " + agentId)))
                    .collect(Collectors.toSet());
            existingEquipe.getAgents().forEach(agent -> agent.setEquipeTravaux(null));
            existingEquipe.setAgents(nouveauxAgents);
            nouveauxAgents.forEach(agent -> agent.setEquipeTravaux(existingEquipe));
        }

        var updatedEquipe = equipeTravauxRepository.save(existingEquipe);
        return equipeTravauxMapper.toDto(updatedEquipe);
    }

    public void deleteEquipe(Long id) {
        if (!equipeTravauxRepository.existsById(id)) {
            throw new EntityNotFoundException("Equipe non trouvée avec l'ID: " + id);
        }
        equipeTravauxRepository.deleteById(id);
    }
}