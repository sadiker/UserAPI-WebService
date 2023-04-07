package com.example.sadiker.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sadiker.models.IResponse;
import com.example.sadiker.models.MyResponse;
import com.example.sadiker.models.TokenResponse;
import com.example.sadiker.models.User;
import com.example.sadiker.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    private  AuthenticationManager authenticationManager;

    public IResponse register(String name,String email,String password,String role) {
       
        Optional<User> optUser = userRepository.findByEmail(email);
        if(optUser.isPresent()){
            return MyResponse.builder().responseData("Bu email adresiyle önceden kayıt yapılmış.İstek başarısız.").build() ; 
        }
        if(password.length()<8 || password.length()>16) {
            return MyResponse.builder().responseData("Min. 8 Max. 16 karakter uzunluğunda şifre giriniz...").build();
        }
        if(!emailControl(email) ) {
            return MyResponse.builder().responseData("Geçerli bir email adresi giriniz...").build();
        }
        User user=new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        userRepository.save(user);

        String jwtToken =jwtService.generateToken(user);

        return TokenResponse.builder().token(jwtToken).build();

    }

    boolean emailControl(String email) {
        String controllerString = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
     
        Pattern pattern = Pattern.compile(controllerString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
      }

    public IResponse login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        BCryptPasswordEncoder decode = new BCryptPasswordEncoder();
        if(user.isPresent() && decode.matches(password, user.get().getPassword()) ) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String token = jwtService.generateToken(user.get());
            return TokenResponse.builder().token(token).build();
            
        } else {
            return MyResponse.builder().responseData("Hatalı şifre veya email girildi").build();
        }
       

    }

    public MyResponse getAll() {
        return new MyResponse(userRepository.findAll());
    }

    public MyResponse getUser() {
        User user = (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
          return MyResponse.builder().responseData(user).build();
    }

    public User forTrying() {
        return userRepository.findByEmail("firstemail@firstemail.com").get();
    }
}
