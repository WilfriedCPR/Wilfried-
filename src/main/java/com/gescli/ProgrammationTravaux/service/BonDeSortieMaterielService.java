package com.gescli.ProgrammationTravaux.service;

import com.gescli.ProgrammationTravaux.dto.BonDeSortieMaterielDto;
import com.gescli.ProgrammationTravaux.mapper.BonDeSortieMaterielMapper;
import com.gescli.ProgrammationTravaux.model.DemandeDeMateriel;
import com.gescli.ProgrammationTravaux.repository.BonDeSortieMaterielRepository;
import com.gescli.ProgrammationTravaux.repository.DemandeDeMaterielRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BonDeSortieMaterielService {

    @Autowired
    private BonDeSortieMaterielRepository bonDeSortieMaterielRepository;

    @Autowired
    private BonDeSortieMaterielMapper bonDeSortieMaterielMapper;

    @Autowired
    private DemandeDeMaterielRepository demandeDeMaterielRepository;

    public BonDeSortieMaterielDto createBonDeSortie(BonDeSortieMaterielDto bonDeSortieMaterielDto) {
        DemandeDeMateriel demande = demandeDeMaterielRepository.findById(bonDeSortieMaterielDto.getIdDemandeMateriel())
            .orElseThrow(() -> new EntityNotFoundException("Demande de matériel non trouvée avec l'ID : " + bonDeSortieMaterielDto.getIdDemandeMateriel()));
        var bonDeSortie = bonDeSortieMaterielMapper.toEntity(bonDeSortieMaterielDto);

        bonDeSortie.setDemandeMateriel(demande);

        if (bonDeSortie.getStatut() == null || bonDeSortie.getStatut().isEmpty()) {
            bonDeSortie.setStatut("EN_ATTENTE");
        }

        var savedBon = bonDeSortieMaterielRepository.save(bonDeSortie);
        return bonDeSortieMaterielMapper.toDto(savedBon);
    }

    public BonDeSortieMaterielDto getBonDeSortieById(Long id) {
        return bonDeSortieMaterielRepository.findById(id)
            .map(bonDeSortieMaterielMapper::toDto)
            .orElseThrow(() -> new EntityNotFoundException("Bon de sortie non trouvé avec l'ID : " + id));
    }

    public List<BonDeSortieMaterielDto> getBonsDeSortieByDemandeId(Long idDemande) {
        return bonDeSortieMaterielRepository.findByDemandeMaterielIdDemandeMateriel(idDemande).stream()
            .map(bonDeSortieMaterielMapper::toDto)
            .collect(Collectors.toList());
    }

    public BonDeSortieMaterielDto updateBonDeSortie(Long id, BonDeSortieMaterielDto bonDeSortieMaterielDto) {
        return bonDeSortieMaterielRepository.findById(id).map(bon -> {
            bon.setDateEmission(bonDeSortieMaterielDto.getDateEmission());
            bon.setQuantiteDemandee(bonDeSortieMaterielDto.getQuantiteDemandee());
            bon.setStatut(bonDeSortieMaterielDto.getStatut());
            bon.setSignature(bonDeSortieMaterielDto.getSignature());

            if (!bon.getDemandeMateriel().getIdDemandeMateriel().equals(bonDeSortieMaterielDto.getIdDemandeMateriel())) {
                DemandeDeMateriel nouvelleDemande = demandeDeMaterielRepository.findById(bonDeSortieMaterielDto.getIdDemandeMateriel())
                    .orElseThrow(() -> new EntityNotFoundException("Nouvelle demande de matériel non trouvée avec l'ID : " + bonDeSortieMaterielDto.getIdDemandeMateriel()));
                bon.setDemandeMateriel(nouvelleDemande);
            }
            var updatedBon = bonDeSortieMaterielRepository.save(bon);
            return bonDeSortieMaterielMapper.toDto(updatedBon);
        }).orElseThrow(() -> new EntityNotFoundException("Bon de sortie non trouvé avec l'ID : " + id));
    }
    public void deleteBonDeSortie(Long id) {
        bonDeSortieMaterielRepository.deleteById(id);
    }
}