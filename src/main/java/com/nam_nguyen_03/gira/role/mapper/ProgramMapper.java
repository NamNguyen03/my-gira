package com.nam_nguyen_03.gira.role.mapper;

import com.nam_nguyen_03.gira.role.dto.ProgramDTO;
import com.nam_nguyen_03.gira.role.dto.ProgramResponseDTO;
import com.nam_nguyen_03.gira.role.model.GiraProgram;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProgramMapper {
    ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);

    ProgramResponseDTO toProgramResponseDTO(GiraProgram program);

    GiraProgram toModel(ProgramDTO program);
}
