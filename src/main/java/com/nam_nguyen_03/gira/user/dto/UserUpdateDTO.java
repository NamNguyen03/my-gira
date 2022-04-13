package com.nam_nguyen_03.gira.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateDTO {

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
}
