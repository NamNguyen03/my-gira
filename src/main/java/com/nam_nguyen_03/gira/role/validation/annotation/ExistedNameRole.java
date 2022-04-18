package com.nam_nguyen_03.gira.role.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.nam_nguyen_03.gira.role.validation.validators.ExistedNameRoleValidator;

@Constraint(validatedBy = ExistedNameRoleValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExistedNameRole {
    String message() default "Name already used.";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
