package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.ContrainteDto;
import com.gescli.ProgrammationTravaux.mapper.ContrainteMapper;
import com.gescli.ProgrammationTravaux.model.Contrainte;
import com.gescli.ProgrammationTravaux.model.Travail;
import com.gescli.ProgrammationTravaux.repository.ContrainteRepository;
import com.gescli.ProgrammationTravaux.repository.TravailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContrainteService {

    @Autowired
    private ContrainteRepository contrainteRepository;
    @Autowired
    private ContrainteMapper contrainteMapper;
    @Autowired
    private TravailRepository travailRepository;

    public ContrainteDto createContrainte(ContrainteDto contrainteDto) {
        Travail travail = travailRepository.findById(contrainteDto.getIdTravail())
                .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID : " + contrainteDto.getIdTravail()));

        Contrainte contrainte = contrainteMapper.toEntity(contrainteDto);
        contrainte.setTravail(travail);

        Contrainte savedContrainte = contrainteRepository.save(contrainte);
        return contrainteMapper.toDto(savedContrainte);
    }

    public List<ContrainteDto> getContraintesByTravailId(Long idTravail) {
        return contrainteRepository.findByTravailIdTravail(idTravail).stream()
                .map(contrainteMapper::toDto)
                .collect(Collectors.toList());
    }

    public ContrainteDto updateContrainte(Long id, ContrainteDto contrainteDto) {
        return contrainteRepository.findById(id).map(contrainte -> {
            contrainte.setDescription(contrainteDto.getDescription());
            contrainte.setStatut(contrainteDto.getStatut());

            if (!contrainte.getTravail().getIdTravail().equals(contrainteDto.getIdTravail())) {
                Travail travail = travailRepository.findById(contrainteDto.getIdTravail())
                        .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID : " + contrainteDto.getIdTravail()));
                contrainte.setTravail(travail);
            }

            Contrainte updatedContrainte = contrainteRepository.save(contrainte);
            return contrainteMapper.toDto(updatedContrainte);
        }).orElse(null);
    }

    public void deleteContrainte(Long id) {
        contrainteRepository.deleteById(id);
    }
}