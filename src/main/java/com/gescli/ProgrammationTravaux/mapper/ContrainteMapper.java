package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.ContrainteDto;
import com.gescli.ProgrammationTravaux.model.Contrainte;
import com.gescli.ProgrammationTravaux.model.Travail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContrainteMapper {

    @Mapping(source = "travail.idTravail", target = "idTravail")
    ContrainteDto toDto(Contrainte contrainte);

    @Mapping(target = "travail", ignore = true)
    Contrainte toEntity(ContrainteDto contrainteDto);
}