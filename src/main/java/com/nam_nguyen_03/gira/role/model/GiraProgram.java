package com.nam_nguyen_03.gira.role.model;


import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nam_nguyen_03.gira.common.model.BaseEntity;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "gira_program")
public class GiraProgram  extends BaseEntity  {
	private String name;
	
	@Enumerated(EnumType.STRING)
	private GiraModule module;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "program_type")
	private GiraProgramType type;

	private String description;
	

	@JsonIgnore
	@ManyToMany(mappedBy = "programs")
	@Builder.Default
    private Set<GiraRole> roles = new LinkedHashSet();

    private String identityKey;

    public void generateIdentityKey(){
        identityKey = module.name().toLowerCase() + "_"  + type.name().toLowerCase();
    }
}
