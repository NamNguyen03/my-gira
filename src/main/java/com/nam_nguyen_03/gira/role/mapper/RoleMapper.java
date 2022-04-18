package com.nam_nguyen_03.gira.role.mapper;

import com.nam_nguyen_03.gira.role.dto.RoleDTO;
import com.nam_nguyen_03.gira.role.dto.RoleResponseDTO;
import com.nam_nguyen_03.gira.role.model.GiraRole;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    GiraRole toModel(RoleDTO role);

    RoleResponseDTO toRoleResponseDTO(GiraRole role);
    
}
