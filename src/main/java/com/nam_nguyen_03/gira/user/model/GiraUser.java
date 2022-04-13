package com.nam_nguyen_03.gira.user.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import com.nam_nguyen_03.gira.common.model.BaseEntity;

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
}
