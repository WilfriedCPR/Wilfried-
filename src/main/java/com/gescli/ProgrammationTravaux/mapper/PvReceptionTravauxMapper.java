package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.PvReceptionTravauxDto;
import com.gescli.ProgrammationTravaux.model.PVReceptionTravaux;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PvReceptionTravauxMapper {
    @Mapping(source = "travail.idTravail", target = "idTravail")
    PvReceptionTravauxDto toDto(PVReceptionTravaux pvReceptionTravaux);

    @Mapping(target = "travail", ignore = true)
    PVReceptionTravaux toEntity(PvReceptionTravauxDto pvReceptionTravauxDto);
}