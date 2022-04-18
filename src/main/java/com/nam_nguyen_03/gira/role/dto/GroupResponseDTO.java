package com.nam_nguyen_03.gira.role.dto;

import java.util.Set;
import java.util.UUID;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponseDTO {
    private UUID id;

    private String name;

    private String description;

    private Set<RoleResponseDTO> roles;
}
