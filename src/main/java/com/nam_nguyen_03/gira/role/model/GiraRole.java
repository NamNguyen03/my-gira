package com.nam_nguyen_03.gira.role.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nam_nguyen_03.gira.common.model.BaseEntity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "gira_role")
public class GiraRole extends BaseEntity {
    private String name;
    private String description;

    @JsonIgnore
	@ManyToMany(mappedBy = "roles")
    @Builder.Default
    private Set<GiraGroup> groups = new LinkedHashSet(); 

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(
		name 		= "gira_role_program",
		joinColumns = @JoinColumn(name = "role_id"),
		inverseJoinColumns = @JoinColumn(name = "program_id")
	)
    @Builder.Default
    private Set<GiraProgram> programs = new LinkedHashSet();

    public void addProgram(GiraProgram program) {
        programs.add(program);
        program.getRoles().add(this);

    }
}
