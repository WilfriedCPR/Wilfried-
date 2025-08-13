package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.AgentDto;
import com.gescli.ProgrammationTravaux.model.Agent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgentMapper {

    @Mapping(source = "equipeTravaux.idEquipe", target = "idEquipe")
    AgentDto toDto(Agent agent);
    Agent toEntity(AgentDto agentDto);
    List<AgentDto> toDtoList(List<Agent> agents);
    }
