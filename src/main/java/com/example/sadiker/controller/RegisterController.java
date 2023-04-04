package com.example.sadiker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sadiker.models.IResponse;

import com.example.sadiker.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;


@RestController
@Api(value = "Kullanıcı kayıt Controller")
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    @ApiOperation("Yeni kullanıcı kaydetme metodudur.")
    public IResponse register(@Valid @RequestParam("name") String name,
    @Valid @RequestParam("email") String email,
    @RequestParam("password") String password,
    @Valid @RequestParam("role") String role
    ) {
        return userService.register(name,email,password,role);
    }
}
