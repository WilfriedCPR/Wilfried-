package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.PlanningTravauxDto;
import com.gescli.ProgrammationTravaux.model.PlanningTravaux;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EquipeTravauxMapper.class, TravailMapper.class})
public interface PlanningTravauxMapper {
    @Mapping(source = "equipeTravaux.idEquipe", target = "equipeTravauxId")
    PlanningTravauxDto toDto(PlanningTravaux planningTravaux);

    @Mapping(target = "equipeTravaux", ignore = true)
    @Mapping(target = "travaux", ignore = true)
    PlanningTravaux toEntity(PlanningTravauxDto planningTravauxDto);
}