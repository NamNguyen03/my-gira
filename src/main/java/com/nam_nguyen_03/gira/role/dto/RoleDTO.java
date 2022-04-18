package com.nam_nguyen_03.gira.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.nam_nguyen_03.gira.role.validation.annotation.ExistedNameRole;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleDTO {
    @Size(min = 3, max = 100, message = "{role.name.size}")
    @ExistedNameRole(message = "{group.name.existed}")
    private String name;
    
    @NotBlank(message = "{role.description.not-blank}")
    private String description;
   
}
