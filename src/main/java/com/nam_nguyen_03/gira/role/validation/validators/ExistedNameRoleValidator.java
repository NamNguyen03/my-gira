package com.nam_nguyen_03.gira.role.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nam_nguyen_03.gira.role.repository.GiraRoleRepository;
import com.nam_nguyen_03.gira.role.validation.annotation.ExistedNameRole;

import org.springframework.beans.factory.annotation.Autowired;

public class ExistedNameRoleValidator implements ConstraintValidator<ExistedNameRole, String> {

    private String message;
    
    @Autowired
    private GiraRoleRepository repository;

    @Override
    public void initialize(ExistedNameRole existsNameRole) {
        message = existsNameRole.message();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        
        if(!repository.existsByName(name)) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(message)
			.addConstraintViolation()
			.disableDefaultConstraintViolation();

        return false;
    } 

}
