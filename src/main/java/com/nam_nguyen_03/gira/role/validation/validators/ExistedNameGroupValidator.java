package com.nam_nguyen_03.gira.role.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nam_nguyen_03.gira.role.repository.GiraGroupRepository;
import com.nam_nguyen_03.gira.role.validation.annotation.ExistedNameGroup;

import org.springframework.beans.factory.annotation.Autowired;

public class ExistedNameGroupValidator  implements ConstraintValidator<ExistedNameGroup, String> {

    private String message;
    
    @Autowired
    private GiraGroupRepository repository;

    @Override
    public void initialize(ExistedNameGroup existedNameGroup) {
        message = existedNameGroup.message();
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
