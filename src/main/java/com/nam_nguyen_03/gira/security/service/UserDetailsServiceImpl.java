package com.nam_nguyen_03.gira.security.service;

import java.util.ArrayList;

import com.nam_nguyen_03.gira.user.model.GiraUser;
import com.nam_nguyen_03.gira.user.repository.GiraUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	 @Autowired
	 private GiraUserRepository repository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 GiraUser user = repository.findByUsername(username)
		 	.orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: " + username));
	
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}
	
}
