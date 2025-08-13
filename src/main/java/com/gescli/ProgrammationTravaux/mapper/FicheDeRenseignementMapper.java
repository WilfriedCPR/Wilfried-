package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.FicheDeRenseignementDto;
import com.gescli.ProgrammationTravaux.model.FicheDeRenseignement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FicheDeRenseignementMapper {

    @Mapping(source = "travail.idTravail", target = "idTravail")
    FicheDeRenseignementDto toDto(FicheDeRenseignement ficheDeRenseignement);

    @Mapping(target = "travail", ignore = true)
    FicheDeRenseignement toEntity(FicheDeRenseignementDto ficheDeRenseignementDto);
}
