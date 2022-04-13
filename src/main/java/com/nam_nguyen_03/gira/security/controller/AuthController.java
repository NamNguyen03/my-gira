package com.nam_nguyen_03.gira.security.controller;

import javax.validation.Valid;

import com.nam_nguyen_03.gira.common.util.ResponseHelper;
import com.nam_nguyen_03.gira.security.dto.LoginDTO;
import com.nam_nguyen_03.gira.security.dto.RegisterDTO;
import com.nam_nguyen_03.gira.security.service.UserAuthService;
import com.nam_nguyen_03.gira.user.dto.UserResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    
    @Autowired
    private UserAuthService service;

    @PostMapping("register")
    public Object register( @Valid @RequestBody RegisterDTO rq, BindingResult result) {
        if(result.hasErrors()) {
			return ResponseHelper.getResponse(result, HttpStatus.BAD_REQUEST, true);
		}

        UserResponseDTO rp = service.register(rq);

        return ResponseHelper.getResponse(rp, HttpStatus.CREATED, false);
    }

    @PostMapping("login")
    public Object login(@Valid @RequestBody LoginDTO rq, BindingResult result){
        if(result.hasErrors()) {
			return ResponseHelper.getResponse(result, HttpStatus.BAD_REQUEST, true);
		}

        String rp = service.login(rq);

        return ResponseHelper.getResponse(rp, HttpStatus.OK, false);
    }
}
