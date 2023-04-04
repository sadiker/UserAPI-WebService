package com.example.sadiker.controller;


import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sadiker.models.MyResponse;
import com.example.sadiker.models.User;
import com.example.sadiker.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("Genel  Controller")
public class GeneralController {

    @Autowired
    UserService userService;
    
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/userlist")
    @ApiOperation("Tüm kullanıcıları gösteren metottur.(Sadece ADMIN erişebilir.) ")
    public MyResponse userList() {
        return userService.getAll();
    }
    //@PreAuthorize("hasRole('ADMIN','USER')")
    @GetMapping("/user/me")
    @ApiOperation("Mevcut kullanıcıyı gösteren metottur.(ADMIN ve USER erişebilir.)")
    public MyResponse userMe() {
        return  userService.getUser();
        
    }
    @GetMapping("/fortrying")
    @ApiOperation("Hazır kullanıcı içindir.(Erişim serbesttir.)")
    public User forTrying() {
        return  userService.forTrying();
        
    }
}
