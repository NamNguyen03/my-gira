package com.nam_nguyen_03.gira.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {
    
    @Bean
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource messageSource
			= new ReloadableResourceBundleMessageSource();
		
		messageSource.setBasename("classpath:/validation/message");
		messageSource.setDefaultEncoding("UTF-8");
		
		return messageSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		
		validatorFactoryBean.setValidationMessageSource(getMessageSource());
		
		return validatorFactoryBean;
	}

}
