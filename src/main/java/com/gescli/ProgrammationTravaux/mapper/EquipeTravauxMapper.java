package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.EquipeTravauxDto;
import com.gescli.ProgrammationTravaux.model.EquipeTravaux;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {AgentMapper.class})
public interface EquipeTravauxMapper {
    EquipeTravauxDto toDto(EquipeTravaux equipeTravaux);

    @Mapping(target = "agents", ignore = true)
    @Mapping(target = "planningTravaux", ignore = true)
    EquipeTravaux toEntity(EquipeTravauxDto equipeTravauxDto);

    List<EquipeTravauxDto> toDtoList(List<EquipeTravaux> equipes);
}