package com.example.sadiker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sadiker.models.User;
import com.example.sadiker.repository.UserRepository;

@Service
public class SecurityImp implements UserDetailsService {

    @Autowired
    private UserRepository urepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = urepo.findByEmail(email).get();
        if(user!=null) {
            return  user;
        } else {
            return  new User();
        }
       
    }

}