package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.PlanningTravauxDto;
import com.gescli.ProgrammationTravaux.mapper.PlanningTravauxMapper;
import com.gescli.ProgrammationTravaux.model.EquipeTravaux;
import com.gescli.ProgrammationTravaux.model.PlanningTravaux;
import com.gescli.ProgrammationTravaux.repository.EquipeTravauxRepository;
import com.gescli.ProgrammationTravaux.repository.PlanningTravauxRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanningTravauxService {

    @Autowired
    private PlanningTravauxRepository planningTravauxRepository;
    @Autowired
    private PlanningTravauxMapper planningTravauxMapper;
    @Autowired
    private EquipeTravauxRepository equipeTravauxRepository;

    public PlanningTravauxDto createPlanning(PlanningTravauxDto planningTravauxDto) {
        EquipeTravaux equipeTravaux = equipeTravauxRepository.findById(planningTravauxDto.getEquipeTravauxId())
                .orElseThrow(() -> new EntityNotFoundException("Equipe de travaux non trouvée avec l'ID: " + planningTravauxDto.getEquipeTravauxId()));

        var planning = planningTravauxMapper.toEntity(planningTravauxDto);
        planning.setEquipeTravaux(equipeTravaux);

        var savedPlanning = planningTravauxRepository.save(planning);
        return planningTravauxMapper.toDto(savedPlanning);
    }

    public PlanningTravauxDto getPlanningById(Long id) {
        return planningTravauxRepository.findById(id)
                .map(planningTravauxMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Planning non trouvé avec l'ID: " + id));
    }

    public List<PlanningTravauxDto> getAllPlannings() {
        return planningTravauxRepository.findAll().stream()
                .map(planningTravauxMapper::toDto)
                .collect(Collectors.toList());
    }

    public PlanningTravauxDto updatePlanning(Long id, PlanningTravauxDto planningTravauxDto) {
        PlanningTravaux existingPlanning = planningTravauxRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Planning non trouvé avec l'ID: " + id));

        existingPlanning.setNomPlanning(planningTravauxDto.getNomPlanning());
        existingPlanning.setDateDebutPrevue(planningTravauxDto.getDateDebutPrevue());
        existingPlanning.setDateFinPrevue(planningTravauxDto.getDateFinPrevue());
        existingPlanning.setStatut(planningTravauxDto.getStatut());

        if (planningTravauxDto.getEquipeTravauxId() != null && !existingPlanning.getEquipeTravaux().getIdEquipe().equals(planningTravauxDto.getEquipeTravauxId())) {
            EquipeTravaux nouvelleEquipe = equipeTravauxRepository.findById(planningTravauxDto.getEquipeTravauxId())
                    .orElseThrow(() -> new EntityNotFoundException("Equipe de travaux non trouvée avec l'ID: " + planningTravauxDto.getEquipeTravauxId()));
            existingPlanning.setEquipeTravaux(nouvelleEquipe);
        }

        var updatedPlanning = planningTravauxRepository.save(existingPlanning);
        return planningTravauxMapper.toDto(updatedPlanning);
    }
    public void deletePlanning(Long id) {
        if (!planningTravauxRepository.existsById(id)) {
            throw new EntityNotFoundException("Planning non trouvé avec l'ID: " + id);
        }
        planningTravauxRepository.deleteById(id);
    }
}