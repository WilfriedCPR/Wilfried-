package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.BmsDto;
import com.gescli.ProgrammationTravaux.mapper.BmsMapper;
import com.gescli.ProgrammationTravaux.model.BMS;
import com.gescli.ProgrammationTravaux.model.Travail;
import com.gescli.ProgrammationTravaux.repository.BmsRepository;
import com.gescli.ProgrammationTravaux.repository.TravailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BmsService {

    @Autowired
    private BmsRepository bmsRepository;
    @Autowired
    private BmsMapper bmsMapper;
    @Autowired
    private TravailRepository travailRepository;

    public BmsDto createBms(BmsDto bmsDto) {
        Travail travail = travailRepository.findById(bmsDto.getIdTravail())
                .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID : " + bmsDto.getIdTravail()));

        BMS bms = bmsMapper.toEntity(bmsDto);

        bms.setTravail(travail);

        BMS savedBms = bmsRepository.save(bms);
        return bmsMapper.toDto(savedBms);
    }

    public BmsDto getBmsById(Long id) {
        return bmsRepository.findById(id)
                .map(bmsMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("BMS non trouvé avec l'ID : " + id));
    }

    public List<BmsDto> getAllBms() {
        return bmsRepository.findAll().stream()
                .map(bmsMapper::toDto)
                .collect(Collectors.toList());
    }

    public BmsDto updateBms(Long id, BmsDto bmsDto) {
        BMS existingBms = bmsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BMS non trouvé avec l'ID : " + id));

        existingBms.setDateEmission(bmsDto.getDateEmission());
        existingBms.setDateMiseEnService(bmsDto.getDateMiseEnService());

        if (!existingBms.getTravail().getIdTravail().equals(bmsDto.getIdTravail())) {
            Travail nouveauTravail = travailRepository.findById(bmsDto.getIdTravail())
                    .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID : " + bmsDto.getIdTravail()));
            existingBms.setTravail(nouveauTravail);
        }
        BMS updatedBms = bmsRepository.save(existingBms);
        return bmsMapper.toDto(updatedBms);
    }

    public void deleteBms(Long id) {
        bmsRepository.deleteById(id);
    }
}