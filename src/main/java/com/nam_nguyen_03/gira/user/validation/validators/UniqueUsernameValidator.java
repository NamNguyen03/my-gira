package com.nam_nguyen_03.gira.user.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.nam_nguyen_03.gira.user.repository.GiraUserRepository;
import com.nam_nguyen_03.gira.user.validation.annotation.UniqueUsername;

import org.springframework.beans.factory.annotation.Autowired;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private String message;
    
    @Autowired
    private GiraUserRepository repository;

    @Override
    public void initialize(UniqueUsername uniqueUsername) {
        message = uniqueUsername.message();
    }
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if(username == null || username.length() == 0){
			return false;
        }
		
		if(!repository.existsByUsername(username)) {
			return true;
		}
		
		context.buildConstraintViolationWithTemplate(message)
			.addConstraintViolation()
			.disableDefaultConstraintViolation();
		return false;
	}
	
}
