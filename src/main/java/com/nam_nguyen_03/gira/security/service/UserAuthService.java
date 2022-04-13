package com.nam_nguyen_03.gira.security.service;

import com.nam_nguyen_03.gira.security.dto.LoginDTO;
import com.nam_nguyen_03.gira.security.dto.RegisterDTO;
import com.nam_nguyen_03.gira.user.dto.UserResponseDTO;

public interface UserAuthService {

    UserResponseDTO register(RegisterDTO rq);

    String login(LoginDTO rq);
    
}
