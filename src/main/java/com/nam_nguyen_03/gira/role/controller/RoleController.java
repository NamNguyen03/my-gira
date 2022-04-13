package com.nam_nguyen_03.gira.role.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/roles")
public class RoleController {
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

}
