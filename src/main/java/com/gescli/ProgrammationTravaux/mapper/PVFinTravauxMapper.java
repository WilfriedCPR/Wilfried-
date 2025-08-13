package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.PVFinTravauxDto;
import com.gescli.ProgrammationTravaux.model.PVFinTravaux;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PVFinTravauxMapper {
    @Mapping(source = "travail.idTravail", target = "idTravail")
    PVFinTravauxDto toDto(PVFinTravaux pvFinTravaux);

    @Mapping(target = "travail", ignore = true)
    PVFinTravaux toEntity(PVFinTravauxDto pvFinTravauxDto);
}
