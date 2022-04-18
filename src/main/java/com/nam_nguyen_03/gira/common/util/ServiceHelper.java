package com.nam_nguyen_03.gira.common.util;

import java.util.Optional;
import java.util.UUID;

import com.nam_nguyen_03.gira.common.exception.BusinessException;
import com.nam_nguyen_03.gira.common.model.BaseEntity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@PropertySources({ @PropertySource("classpath:/validation/message.properties") })
public class ServiceHelper<T extends BaseEntity> {
    
    @Value("${entity.id.invalid}")
    private String errorsIdInvalid;

    public T getEntityById(String id , JpaRepository<T, UUID> repository , String errorNotFound) {
        UUID uuid;
        try{
            uuid = UUID.fromString(id);
        }catch(Exception e){
            throw new BusinessException(errorsIdInvalid);
        }
        
        Optional<T> roleOpt = repository.findById(uuid);

        if(roleOpt.isEmpty()){
            throw new BusinessException(errorNotFound);
        }
        return roleOpt.get();
    }
}
