package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.TravailDto;
import com.gescli.ProgrammationTravaux.model.Travail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ContrainteMapper.class, PVFinTravauxMapper.class, DemandeDeMaterielMapper.class, FicheDeRenseignementMapper.class, BmsMapper.class, PvReceptionTravauxMapper.class})
public interface TravailMapper {

    @Mapping(source = "planningTravaux.idPlanning", target = "planningTravauxId")
    TravailDto toDto(Travail travail);

    @Mapping(target = "planningTravaux", ignore = true)
    @Mapping(target = "demandeMateriel", ignore = true)
    Travail toEntity(TravailDto travailDto);

    List<TravailDto> toDtoList(List<Travail> travaux);
}