package com.nam_nguyen_03.gira.common.audit;
import java.util.Optional;

import com.nam_nguyen_03.gira.common.util.UserPrincipal;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String>{

    @Override
    public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable(UserPrincipal.getUsernameCurrent());
    } 
    
}

