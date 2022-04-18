package com.nam_nguyen_03.gira.role.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nam_nguyen_03.gira.role.repository.ProgramRepository;
import com.nam_nguyen_03.gira.role.validation.annotation.ExistedNameProgram;

import org.springframework.beans.factory.annotation.Autowired;

public class ExistedNameProgramValidator  implements ConstraintValidator<ExistedNameProgram, String> {

    private String message;
    
    @Autowired
    private ProgramRepository repository;

    @Override
    public void initialize(ExistedNameProgram existedNameGroup) {
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
