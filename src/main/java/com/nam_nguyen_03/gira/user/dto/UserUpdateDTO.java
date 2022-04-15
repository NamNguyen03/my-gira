package com.nam_nguyen_03.gira.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateDTO {

	@Size(min = 6, max = 100, message = "{user.password.size}")
	private String password;

	private String displayName;
	
	@Email(message = "{user.email.invalid}")
    @Size(min = 3, max = 100, message = "{user.email.size}")
	private String email;

    private String firstName;
 	
	private String lastName;

	private String avatar;
	
	private String department;
	
	private String major;
	
	private String hobbies;
	
	private String facebook;
}
