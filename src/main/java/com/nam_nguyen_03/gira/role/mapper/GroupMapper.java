package com.nam_nguyen_03.gira.role.mapper;

import com.nam_nguyen_03.gira.role.dto.GroupDTO;
import com.nam_nguyen_03.gira.role.dto.GroupResponseDTO;
import com.nam_nguyen_03.gira.role.model.GiraGroup;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GiraGroup toModel(GroupDTO group);

    GroupResponseDTO toGroupResponseDTO(GiraGroup group);
}
