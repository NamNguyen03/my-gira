package com.nam_nguyen_03.gira.user.mapper;

import javax.validation.Valid;

import com.nam_nguyen_03.gira.security.dto.RegisterDTO;
import com.nam_nguyen_03.gira.user.dto.UserResponseDTO;
import com.nam_nguyen_03.gira.user.model.GiraUser;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    GiraUser toModel(@Valid RegisterDTO rq);

    UserResponseDTO toUserResponseDTO(GiraUser save);
}
