package com.nam_nguyen_03.gira.security.service;


import java.util.Optional;

import javax.validation.Valid;

import com.nam_nguyen_03.gira.common.exception.BusinessException;
import com.nam_nguyen_03.gira.security.dto.LoginDTO;
import com.nam_nguyen_03.gira.security.dto.RegisterDTO;
import com.nam_nguyen_03.gira.security.jwt.JwtHelper;
import com.nam_nguyen_03.gira.user.dto.UserResponseDTO;
import com.nam_nguyen_03.gira.user.mapper.UserMapper;
import com.nam_nguyen_03.gira.user.model.GiraUser;
import com.nam_nguyen_03.gira.user.repository.GiraUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@PropertySources({ @PropertySource("classpath:/validation/message.properties") })
public class UserAuthServiceImpl implements UserAuthService{

    @Value("${user.username.not-existed}")
    private String messageUserNotExisted;

    @Value("${user.password.not-equals}")
    private String messagePasswordNotEquals;

    @Autowired
    private GiraUserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
	private JwtHelper jwts;

    @Override
    public UserResponseDTO register(RegisterDTO rq) {
        rq.setPassword(encoder.encode(rq.getPassword()));
        return UserMapper.INSTANCE.toUserResponseDTO(userRepository.save(UserMapper.INSTANCE.toModel(rq)));
    }

    @Override
    public String login(LoginDTO rq) {

        if(!userRepository.existsByUsername(rq.getUsername())){
            throw new BusinessException(messageUserNotExisted);
        }

       // get user information
		Optional<GiraUser> userOpt = userRepository.findByUsername(rq.getUsername());
		
		// check password
		String encodedPassword = userOpt.get().getPassword();
		
		if(!encoder.matches(rq.getPassword(), encodedPassword)) {
			throw new BusinessException(messagePasswordNotEquals);
		}
		
		return jwts.generateJwtToken(rq.getUsername());
    }
    
}
