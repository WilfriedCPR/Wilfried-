package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.TravailDto;
import com.gescli.ProgrammationTravaux.mapper.TravailMapper;
import com.gescli.ProgrammationTravaux.model.PlanningTravaux;
import com.gescli.ProgrammationTravaux.model.Travail;
import com.gescli.ProgrammationTravaux.repository.PlanningTravauxRepository;
import com.gescli.ProgrammationTravaux.repository.TravailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravailService {

    @Autowired
    private TravailRepository travailRepository;
    @Autowired
    private PlanningTravauxRepository planningTravauxRepository;
    @Autowired
    private TravailMapper travailMapper;


    public TravailDto createTravail(TravailDto travailDto) {
        if (travailDto.getPlanningTravauxId() == null) {
            throw new IllegalArgumentException("L'ID du planning est requis pour créer un travail.");
        }

        PlanningTravaux planningTravaux = planningTravauxRepository.findById(travailDto.getPlanningTravauxId())
                .orElseThrow(() -> new EntityNotFoundException("PlanningTravaux non trouvé avec l'ID: " + travailDto.getPlanningTravauxId()));

        var travail = travailMapper.toEntity(travailDto);

        travail.setPlanningTravaux(planningTravaux);


        var savedTravail = travailRepository.save(travail);

        return travailMapper.toDto(savedTravail);
    }

    public TravailDto getTravailById(Long id) {
        var travail = travailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID: " + id));
        return travailMapper.toDto(travail);
    }

    public List<TravailDto> getAllTravaux() {
        return travailRepository.findAll().stream()
                .map(travailMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TravailDto> getTravauxByStatut(String statut) {
        return travailRepository.findByStatutTravaux(statut).stream()
                .map(travailMapper::toDto)
                .collect(Collectors.toList());
    }

    public TravailDto updateStatutTravail(Long id, String nouveauStatut) {
        var existingTravail = travailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID: " + id));
        existingTravail.setStatutTravaux(nouveauStatut);
        var updatedTravail = travailRepository.save(existingTravail);
        return travailMapper.toDto(updatedTravail);
    }

    public TravailDto updateTravail(Long id, TravailDto travailDto) {
        Travail existingTravail = travailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID: " + id));

        existingTravail.setDescription(travailDto.getDescription());
        existingTravail.setDateDebutPrevue(travailDto.getDateDebutPrevue());
        existingTravail.setDateFinPrevue(travailDto.getDateFinPrevue());
        existingTravail.setStatutTravaux(travailDto.getStatutTravaux());
        existingTravail.setRapportExecution(travailDto.getRapportExecution());
        existingTravail.setTypeBranchement(travailDto.getTypeBranchement());

        if (travailDto.getPlanningTravauxId() != null && !existingTravail.getPlanningTravaux().getIdPlanning().equals(travailDto.getPlanningTravauxId())) {
            PlanningTravaux nouveauPlanning = planningTravauxRepository.findById(travailDto.getPlanningTravauxId())
                    .orElseThrow(() -> new EntityNotFoundException("PlanningTravaux non trouvé avec l'ID: " + travailDto.getPlanningTravauxId()));
            existingTravail.setPlanningTravaux(nouveauPlanning);
        }

        var updatedTravail = travailRepository.save(existingTravail);
        return travailMapper.toDto(updatedTravail);
    }

    public void deleteTravail(Long id) {
        if (!travailRepository.existsById(id)) {
            throw new EntityNotFoundException("Travail non trouvé avec l'ID: " + id);
        }
        travailRepository.deleteById(id);
    }
}