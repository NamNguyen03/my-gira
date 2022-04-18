package com.nam_nguyen_03.gira.security.authorization;

import com.nam_nguyen_03.gira.common.exception.UnauthorizedException;
import com.nam_nguyen_03.gira.common.util.UserPrincipal;
import com.nam_nguyen_03.gira.role.repository.ProgramRepository;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class AuthorizationAspect {
    
    @Autowired
	private ProgramRepository programRepository;

    @Before("@annotation(giraPermission))")
	public void programAuthorizer (GiraPermission giraPermission) {
		String name = giraPermission.value();
		String username = UserPrincipal.getUsernameCurrent();
		
		boolean isAuthorized = programRepository.existsByNameProgramAndUsername(name, username);
		
		if (!isAuthorized){
            throw new UnauthorizedException("Unauthorized method " + name);
        }
			
	}
}
