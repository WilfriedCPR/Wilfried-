package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.MaterielDto;
import com.gescli.ProgrammationTravaux.mapper.MaterielMapper;
import com.gescli.ProgrammationTravaux.model.Materiel;
import com.gescli.ProgrammationTravaux.repository.MaterielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterielService {

    @Autowired
    private MaterielRepository materielRepository;

    @Autowired
    private MaterielMapper materielMapper;

    public MaterielDto createMateriel(MaterielDto materielDto) {
        var materiel = materielMapper.toEntity(materielDto);
        var savedMateriel = materielRepository.save(materiel);
        return materielMapper.toDto(savedMateriel);
    }

    public List<MaterielDto> getAllMateriels() {
        return materielRepository.findAll().stream()
                .map(materielMapper::toDto)
                .collect(Collectors.toList());
    }
    public MaterielDto getMaterielById(Long id) {
        return materielRepository.findById(id)
                .map(materielMapper::toDto)
                .orElse(null);
    }
    public MaterielDto updateMateriel(Long id, MaterielDto materielDto) {
        return materielRepository.findById(id).map(materiel -> {
            materiel.setNomMateriel(materielDto.getNomMateriel());
            materiel.setDescription(materielDto.getDescription());
            materiel.setQuantiteEnStock(materielDto.getQuantiteEnStock());
            Materiel updatedMateriel = materielRepository.save(materiel);
            return materielMapper.toDto(updatedMateriel);
        }).orElse(null);
    }
    public void deleteMateriel(Long id) {
        materielRepository.deleteById(id);
    }
}