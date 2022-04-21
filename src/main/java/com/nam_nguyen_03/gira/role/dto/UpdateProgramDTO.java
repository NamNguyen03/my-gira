package com.nam_nguyen_03.gira.role.dto;

import com.nam_nguyen_03.gira.role.model.GiraModule;
import com.nam_nguyen_03.gira.role.model.GiraProgramType;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateProgramDTO {
    
    private String name;

    private String description;

    private GiraModule module = null;
	
	private GiraProgramType type = null;
}
