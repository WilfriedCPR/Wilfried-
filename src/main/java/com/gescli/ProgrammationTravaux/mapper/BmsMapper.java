package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.BmsDto;
import com.gescli.ProgrammationTravaux.model.BMS;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BmsMapper {

    @Mapping(source = "travail.idTravail", target = "idTravail")
    BmsDto toDto(BMS bms);

    @Mapping(target = "travail", ignore = true)
    BMS toEntity(BmsDto bmsDto);
}