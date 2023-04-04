package com.example.sadiker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sadiker.models.IResponse;
import com.example.sadiker.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Kullanıcı giriş API")
public class LoginController {
    
    @Autowired
    UserService userService;

    @GetMapping("/login")
    @ApiOperation(value = "Kayıtlı kullanıcı 'login' metodudur.")
    public IResponse login( @RequestParam("email") String email,
     @RequestParam("password") String password) {
        return userService.login(email,password);
    }
}
