package com.nam_nguyen_03.gira.security.dto;

import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDTO {
    @Size(min = 3, max = 100, message = "{user.username.size}")
    private String username;
    @Size(min = 6, max = 100, message = "{user.password.size}")
    private String password;
}
