package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.FicheDeRenseignementDto;
import com.gescli.ProgrammationTravaux.mapper.FicheDeRenseignementMapper;
import com.gescli.ProgrammationTravaux.model.Travail;
import com.gescli.ProgrammationTravaux.repository.FicheDeRenseignementRepository;
import com.gescli.ProgrammationTravaux.repository.TravailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FicheDeRenseignementService {

    @Autowired
    private FicheDeRenseignementRepository ficheDeRenseignementRepository;

    @Autowired
    private FicheDeRenseignementMapper ficheDeRenseignementMapper;

    @Autowired
    private TravailRepository travailRepository;

    public FicheDeRenseignementDto createFiche(FicheDeRenseignementDto ficheDeRenseignementDto) {
        Travail travail = travailRepository.findById(ficheDeRenseignementDto.getIdTravail())
                .orElseThrow(() -> new RuntimeException("Travail non trouvé"));
        var fiche = ficheDeRenseignementMapper.toEntity(ficheDeRenseignementDto);
        fiche.setTravail(travail);
        var savedFiche = ficheDeRenseignementRepository.save(fiche);
        return ficheDeRenseignementMapper.toDto(savedFiche);
    }

    public FicheDeRenseignementDto getFicheById(Long id) {
        var fiche = ficheDeRenseignementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fiche de renseignement non trouvée"));
        return ficheDeRenseignementMapper.toDto(fiche);
    }

    public List<FicheDeRenseignementDto> getAllFiches() {
        return ficheDeRenseignementRepository.findAll().stream()
                .map(ficheDeRenseignementMapper::toDto)
                .collect(Collectors.toList());
    }

    public FicheDeRenseignementDto updateFiche(Long id, FicheDeRenseignementDto ficheDeRenseignementDto) {
        var existingFiche = ficheDeRenseignementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fiche de renseignement non trouvée"));
        existingFiche.setObservationsTerrain(ficheDeRenseignementDto.getObservationsTerrain());

        if (!existingFiche.getTravail().getIdTravail().equals(ficheDeRenseignementDto.getIdTravail())) {
            Travail travail = travailRepository.findById(ficheDeRenseignementDto.getIdTravail())
                    .orElseThrow(() -> new RuntimeException("Travail non trouvé"));
             existingFiche.setTravail(travail);
        }

        var updatedFiche = ficheDeRenseignementRepository.save(existingFiche);
        return ficheDeRenseignementMapper.toDto(updatedFiche);
    }

    public void deleteFiche(Long id) {
        ficheDeRenseignementRepository.deleteById(id);
    }
}