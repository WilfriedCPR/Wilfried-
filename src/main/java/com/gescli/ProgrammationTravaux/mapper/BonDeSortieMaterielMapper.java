package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.BonDeSortieMaterielDto;
import com.gescli.ProgrammationTravaux.model.BonDeSortieMateriel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BonDeSortieMaterielMapper {

    @Mapping(source = "demandeMateriel.idDemandeMateriel", target = "idDemandeMateriel")
    BonDeSortieMaterielDto toDto(BonDeSortieMateriel bonDeSortieMateriel);

    @Mapping(target = "demandeMateriel", ignore = true)
    BonDeSortieMateriel toEntity(BonDeSortieMaterielDto bonDeSortieMaterielDto);
}