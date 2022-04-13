package com.nam_nguyen_03.gira.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.nam_nguyen_03.gira.user.model.UserStatus;
import com.nam_nguyen_03.gira.user.validation.annotation.UniqueEmail;
import com.nam_nguyen_03.gira.user.validation.annotation.UniqueUsername;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @Size(min = 3, max = 100, message = "{user.username.size}")
	@UniqueUsername(message = "{user.username.existed}")
	private String username;

    @Size(min = 6, max = 100, message = "{user.password.size}")
	private String password;

	@NotBlank(message = "{user.display-name.not-blank")
	private String displayName;
	
    @Email(message = "{user.email.invalid}")
	@UniqueEmail(message = "{user.email.existed}")
    @Size(min = 3, max = 100, message = "{user.email.size}")
	private String email;

	private UserStatus status = UserStatus.ACTIVE;

    private String firstName;

    private String lastName;
}
