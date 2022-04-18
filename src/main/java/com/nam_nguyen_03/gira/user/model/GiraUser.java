package com.nam_nguyen_03.gira.user.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;
import com.nam_nguyen_03.gira.common.model.BaseEntity;
import com.nam_nguyen_03.gira.role.model.GiraGroup;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "gira_user")
public class GiraUser extends BaseEntity {
    
	private String username;

	private String password;

	private String displayName;
	
	private String email;
	
    @Enumerated(EnumType.STRING)
	private UserStatus status;

    private String firstName;
 	
	private String lastName;

	private String avatar;
	
	private String department;
	
	private String major;
	
	private String hobbies;
	
	private String facebook;

	@Builder.Default
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(
		name 		= "gira_group_user",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "group_id")
	)
	private Set<GiraGroup> groups = new LinkedHashSet();

	public void addGroup( GiraGroup group ) {
		groups.add(group);
		group.getUsers().add(this);
	}
}
