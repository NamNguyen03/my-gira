package com.nam_nguyen_03.gira.role.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;
import com.nam_nguyen_03.gira.common.model.BaseEntity;
import com.nam_nguyen_03.gira.user.model.GiraUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "gira_group")
public class GiraGroup extends BaseEntity {
    
    private String name;
    private String description;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
		name 		= "gira_group_role",
		joinColumns = @JoinColumn(name = "group_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")
	)
    @Builder.Default
    private Set<GiraRole> roles = new LinkedHashSet();

   
    @Builder.Default
	@JsonIgnore
	@ManyToMany(mappedBy = "groups")
    private Set<GiraUser> users = new LinkedHashSet();

    public void addRole(GiraRole role) {
		roles.add(role);
		role.getGroups().add(this);
	}
}
