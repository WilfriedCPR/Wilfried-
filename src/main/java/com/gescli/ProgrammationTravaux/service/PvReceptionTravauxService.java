package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.PvReceptionTravauxDto;
import com.gescli.ProgrammationTravaux.mapper.PvReceptionTravauxMapper;
import com.gescli.ProgrammationTravaux.model.Travail;
import com.gescli.ProgrammationTravaux.repository.PvReceptionTravauxRepository;
import com.gescli.ProgrammationTravaux.repository.TravailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PvReceptionTravauxService {

    @Autowired
    private PvReceptionTravauxRepository pvReceptionTravauxRepository;
    @Autowired
    private PvReceptionTravauxMapper pvReceptionTravauxMapper;
    @Autowired
    private TravailRepository travailRepository;

    public PvReceptionTravauxDto createPvReceptionTravaux(PvReceptionTravauxDto pvReceptionTravauxDto) {
        Travail travail = travailRepository.findById(pvReceptionTravauxDto.getIdTravail())
                .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID : " + pvReceptionTravauxDto.getIdTravail()));

        var pvReceptionTravaux = pvReceptionTravauxMapper.toEntity(pvReceptionTravauxDto);

        pvReceptionTravaux.setTravail(travail);

        var savedPV = pvReceptionTravauxRepository.save(pvReceptionTravaux);
        return pvReceptionTravauxMapper.toDto(savedPV);
    }

    public PvReceptionTravauxDto getPvReceptionTravauxById(Long id) {
        return pvReceptionTravauxRepository.findById(id)
                .map(pvReceptionTravauxMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("PV de réception non trouvé avec l'ID : " + id));
    }

    public List<PvReceptionTravauxDto> getAllPvReceptionTravaux() {
        return pvReceptionTravauxRepository.findAll().stream()
                .map(pvReceptionTravauxMapper::toDto)
                .collect(Collectors.toList());
    }

    public PvReceptionTravauxDto updatePvReceptionTravaux(Long id, PvReceptionTravauxDto pvReceptionTravauxDto) {
        return pvReceptionTravauxRepository.findById(id).map(pv -> {
            pv.setDatePv(pvReceptionTravauxDto.getDatePv());
            pv.setObservations(pvReceptionTravauxDto.getObservations());
            pv.setStatutPv(pvReceptionTravauxDto.getStatutPv());

            if (!pv.getTravail().getIdTravail().equals(pvReceptionTravauxDto.getIdTravail())) {
                Travail nouveauTravail = travailRepository.findById(pvReceptionTravauxDto.getIdTravail())
                        .orElseThrow(() -> new EntityNotFoundException("Travail non trouvé avec l'ID : " + pvReceptionTravauxDto.getIdTravail()));
                pv.setTravail(nouveauTravail);
            }

            var updatedPV = pvReceptionTravauxRepository.save(pv);
            return pvReceptionTravauxMapper.toDto(updatedPV);
        }).orElseThrow(() -> new EntityNotFoundException("PV de réception non trouvé avec l'ID : " + id));
    }

    public void deletePvReceptionTravaux(Long id) {
        pvReceptionTravauxRepository.deleteById(id);
    }
}