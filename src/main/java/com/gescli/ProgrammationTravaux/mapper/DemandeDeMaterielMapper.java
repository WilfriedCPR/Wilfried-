package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.DemandeDeMaterielDto;
import com.gescli.ProgrammationTravaux.model.DemandeDeMateriel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {MaterielMapper.class, BonDeSortieMaterielMapper.class})
public interface DemandeDeMaterielMapper {

    @Mapping(source = "travail.idTravail", target = "idTravail")
    DemandeDeMaterielDto toDto(DemandeDeMateriel demandeDeMateriel);

    @Mapping(target = "travail", ignore = true)
    @Mapping(target = "materiels", ignore = true)
    @Mapping(target = "bonsDeSortie", ignore = true)
    DemandeDeMateriel toEntity(DemandeDeMaterielDto demandeDeMaterielDto);

    List<DemandeDeMaterielDto> toDtoList(List<DemandeDeMateriel> demandes);
}