package com.gescli.ProgrammationTravaux.mapper;

import com.gescli.ProgrammationTravaux.dto.MaterielDto;
import com.gescli.ProgrammationTravaux.model.Materiel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaterielMapper {
    MaterielDto toDto(Materiel materiel);
    Materiel toEntity(MaterielDto materielDto);
    List<MaterielDto> toDtoList(List<Materiel> materiels);
}
