package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.DemandeDeMaterielDto;
import com.gescli.ProgrammationTravaux.mapper.DemandeDeMaterielMapper;
import com.gescli.ProgrammationTravaux.model.DemandeDeMateriel;
import com.gescli.ProgrammationTravaux.model.Materiel;
import com.gescli.ProgrammationTravaux.model.Travail;
import com.gescli.ProgrammationTravaux.repository.DemandeDeMaterielRepository;
import com.gescli.ProgrammationTravaux.repository.MaterielRepository;
import com.gescli.ProgrammationTravaux.repository.TravailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DemandeDeMaterielService {

    @Autowired
    private DemandeDeMaterielRepository demandeDeMaterielRepository;
    @Autowired
    private DemandeDeMaterielMapper demandeDeMaterielMapper;
    @Autowired
    private TravailRepository travailRepository;
    @Autowired
    private MaterielRepository materielRepository;

    public DemandeDeMaterielDto createDemande(DemandeDeMaterielDto demandeDeMaterielDto) {
        if (demandeDeMaterielDto.getIdTravail() == null) {
            throw new IllegalArgumentException("L'ID du travail est requis pour créer une demande de matériel.");
        }

        Travail travail = travailRepository.findById(demandeDeMaterielDto.getIdTravail())
                .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID: " + demandeDeMaterielDto.getIdTravail()));

        Set<Materiel> materiels = demandeDeMaterielDto.getMaterielIds().stream()
                .map(id -> materielRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Materiel non trouvé avec l'ID: " + id)))
                .collect(Collectors.toSet());

        DemandeDeMateriel demande = demandeDeMaterielMapper.toEntity(demandeDeMaterielDto);
        demande.setTravail(travail);
        demande.setMateriels(materiels);

        var savedDemande = demandeDeMaterielRepository.save(demande);
        return demandeDeMaterielMapper.toDto(savedDemande);
    }
    public DemandeDeMaterielDto getDemandeById(Long id) {
        var demande = demandeDeMaterielRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demande de matériel non trouvée avec l'ID: " + id));
        return demandeDeMaterielMapper.toDto(demande);
    }

    public List<DemandeDeMaterielDto> getAllDemandes() {
        return demandeDeMaterielRepository.findAll().stream()
                .map(demandeDeMaterielMapper::toDto)
                .collect(Collectors.toList());
    }

    public DemandeDeMaterielDto updateDemande(Long id, DemandeDeMaterielDto demandeDeMaterielDto) {
        DemandeDeMateriel existingDemande = demandeDeMaterielRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Demande de matériel non trouvée avec l'ID: " + id));

        existingDemande.setStatutDemande(demandeDeMaterielDto.getStatutDemande());

        if (demandeDeMaterielDto.getIdTravail() != null && !existingDemande.getTravail().getIdTravail().equals(demandeDeMaterielDto.getIdTravail())) {
             Travail nouveauTravail = travailRepository.findById(demandeDeMaterielDto.getIdTravail())
                    .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID: " + demandeDeMaterielDto.getIdTravail()));
             existingDemande.setTravail(nouveauTravail);
        }

        if (demandeDeMaterielDto.getMaterielIds() != null) {
            Set<Materiel> nouveauxMateriels = demandeDeMaterielDto.getMaterielIds().stream()
                    .map(materielId -> materielRepository.findById(materielId)
                            .orElseThrow(() -> new EntityNotFoundException("Materiel non trouvé avec l'ID: " + materielId)))
                    .collect(Collectors.toSet());
            existingDemande.setMateriels(nouveauxMateriels);
        }

        var updatedDemande = demandeDeMaterielRepository.save(existingDemande);
        return demandeDeMaterielMapper.toDto(updatedDemande);
    }

    public void deleteDemande(Long id) {
        if (!demandeDeMaterielRepository.existsById(id)) {
            throw new EntityNotFoundException("Demande de matériel non trouvée avec l'ID: " + id);
        }
        demandeDeMaterielRepository.deleteById(id);
    }
}