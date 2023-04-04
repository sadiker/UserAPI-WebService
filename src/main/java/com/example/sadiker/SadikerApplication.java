package com.example.sadiker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.sadiker.models.User;
import com.example.sadiker.repository.UserRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
@EnableSwagger2
public class SadikerApplication implements CommandLineRunner {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SadikerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = User.builder()
		.password(passwordEncoder().encode("12345678A"))
		.email("firstemail@firstemail.com")
		.name("FirstName")
		.role("ADMIN")
		.build();

		User user2 = User.builder()
		.password(passwordEncoder().encode("12345678B"))
		.email("secondemail@secondemail.com")
		.name("SecondName")
		.role("USER")
		.build();
		
		userRepository.save(user);
		userRepository.save(user2);
				
	}

}
