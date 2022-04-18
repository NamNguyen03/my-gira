package com.nam_nguyen_03.gira.security.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GiraPermission {
    String value() default "";
	
	Class<?>[] groups() default {};
}
