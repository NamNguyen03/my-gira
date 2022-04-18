package com.nam_nguyen_03.gira.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.nam_nguyen_03.gira.role.model.GiraModule;
import com.nam_nguyen_03.gira.role.model.GiraProgramType;
import com.nam_nguyen_03.gira.role.validation.annotation.ExistedNameProgram;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgramDTO {
    @Size(min = 3, max = 100, message = "{program.name.size}")
    @ExistedNameProgram(message = "{program.name.existed}")
    private String name;
    @NotBlank(message = "{program.description.not-blank}")
    private String description;

    private GiraModule module = GiraModule.ROLE;
	
	private GiraProgramType type = GiraProgramType.READ;
}
