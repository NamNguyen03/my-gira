package com.nam_nguyen_03.gira.security.service;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service

public class UserDetailsServiceImpl implements UserDetailsService {
	// @Autowired
	// private GiraUserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Optional<GiraUser> userOpt = repository.findByUsername(username);
		
		// if(userOpt.isEmpty())
		// 	return null;
		
		// GiraUser currentUser = userOpt.get();
		
		// return new User(currentUser.getUsername(), currentUser.getPassword(), new ArrayList<GrantedAuthority>());

        return new User("", "", new ArrayList<>());
	}
	
}
