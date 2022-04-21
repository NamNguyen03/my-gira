package com.nam_nguyen_03.gira.user.dto;

import java.util.Set;

import com.nam_nguyen_03.gira.role.dto.GroupResponseDTO;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetailsResponseDTO extends UserResponseDTO {
    private Set<GroupResponseDTO> groups;
}
