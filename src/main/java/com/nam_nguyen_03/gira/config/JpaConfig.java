package com.nam_nguyen_03.gira.config;

import com.nam_nguyen_03.gira.common.audit.AuditorAwareImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {
    @Bean
	public AuditorAware<String> auditorAware () {
		return new AuditorAwareImpl();
	}
}
