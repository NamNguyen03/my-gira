package com.nam_nguyen_03.gira.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal {
    
    private UserPrincipal(){

    }

    public static String getUsernameCurrent(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null) {
			return null;
		}
        
		if (auth.getPrincipal() instanceof String) {
			return (String) auth.getPrincipal();
		}

		UserDetails currentAuditor = (UserDetails) auth.getPrincipal();
		return currentAuditor.getUsername();
    }
}
