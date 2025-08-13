package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.PVFinTravauxDto;
import com.gescli.ProgrammationTravaux.mapper.PVFinTravauxMapper;
import com.gescli.ProgrammationTravaux.model.Travail;
import com.gescli.ProgrammationTravaux.repository.PVFinTravauxRepository;
import com.gescli.ProgrammationTravaux.repository.TravailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PvFinTravauxService {

    @Autowired
    private PVFinTravauxRepository pvFinTravauxRepository;
    @Autowired
    private PVFinTravauxMapper pvFinTravauxMapper;
    @Autowired
    private TravailRepository travailRepository;

    public PVFinTravauxDto createPVFinTravaux(PVFinTravauxDto pvFinTravauxDto) {
        Travail travail = travailRepository.findById(pvFinTravauxDto.getIdTravail())
                .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID : " + pvFinTravauxDto.getIdTravail()));

        var pvFinTravaux = pvFinTravauxMapper.toEntity(pvFinTravauxDto);

        pvFinTravaux.setTravail(travail);

        var savedPV = pvFinTravauxRepository.save(pvFinTravaux);
        return pvFinTravauxMapper.toDto(savedPV);
    }

    public PVFinTravauxDto getPVFinTravauxById(Long id) {
        return pvFinTravauxRepository.findById(id)
                .map(pvFinTravauxMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("PV de fin de travaux non trouvé avec l'ID : " + id));
    }

    public List<PVFinTravauxDto> getAllPVFinTravaux() {
        return pvFinTravauxRepository.findAll().stream()
                .map(pvFinTravauxMapper::toDto)
                .collect(Collectors.toList());
    }

    public PVFinTravauxDto updatePVFinTravaux(Long id, PVFinTravauxDto pvFinTravauxDto) {
        return pvFinTravauxRepository.findById(id).map(pv -> {
            pv.setDatePv(pvFinTravauxDto.getDatePv());
            pv.setStatut(pvFinTravauxDto.getStatut());

            if (!pv.getTravail().getIdTravail().equals(pvFinTravauxDto.getIdTravail())) {
                Travail nouveauTravail = travailRepository.findById(pvFinTravauxDto.getIdTravail())
                        .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID : " + pvFinTravauxDto.getIdTravail()));
                pv.setTravail(nouveauTravail);
            }

            var updatedPV = pvFinTravauxRepository.save(pv);
            return pvFinTravauxMapper.toDto(updatedPV);
        }).orElseThrow(() -> new EntityNotFoundException("PV de fin de travaux non trouvé avec l'ID : " + id));
    }

    public void deletePVFinTravaux(Long id) {
        pvFinTravauxRepository.deleteById(id);
    }
}