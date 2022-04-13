package com.nam_nguyen_03.gira.user.dto;

import java.util.UUID;

import com.nam_nguyen_03.gira.user.model.UserStatus;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {
    private UUID id;

	private String username;
	
    private String password;

	private String displayName;
	
	private String email;

    private String firstName;
 	
	private String lastName;

	private String avatar;
	
	private String department;
	
	private String major;
	
	private String hobbies;
	
	private String facebook;

    private UserStatus status;
}
