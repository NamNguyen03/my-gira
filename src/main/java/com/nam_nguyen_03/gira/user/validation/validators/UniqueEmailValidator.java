package com.nam_nguyen_03.gira.user.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.nam_nguyen_03.gira.user.repository.GiraUserRepository;
import com.nam_nguyen_03.gira.user.validation.annotation.UniqueEmail;

import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	private String message;
	
	@Autowired
	private GiraUserRepository repository;
	
	@Override
	public void initialize(UniqueEmail uniqueEmail) {
		message = uniqueEmail.message();
	}
	
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {

		if(!repository.existsByEmail(email)) {
			return true;
		}
		
		context.buildConstraintViolationWithTemplate(message)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
		return false;
	}

}
