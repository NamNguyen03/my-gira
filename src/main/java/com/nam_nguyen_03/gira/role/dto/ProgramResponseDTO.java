package com.nam_nguyen_03.gira.role.dto;

import java.util.UUID;

import com.nam_nguyen_03.gira.role.model.GiraModule;
import com.nam_nguyen_03.gira.role.model.GiraProgramType;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgramResponseDTO {
   
	private UUID id;

	private String name;

	private GiraModule module;
	
	private GiraProgramType type;
	
	private String description;

}
