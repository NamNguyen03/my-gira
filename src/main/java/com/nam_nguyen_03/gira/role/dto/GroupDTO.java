package com.nam_nguyen_03.gira.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.nam_nguyen_03.gira.role.validation.annotation.ExistedNameGroup;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDTO {
    @Size(min = 3, max = 100, message = "{group.name.size}")
    @ExistedNameGroup(message = "{group.name.existed}")
    private String name;
    @NotBlank(message = "{group.description.not-blank}")
    private String description;
}
