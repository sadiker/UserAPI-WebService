package com.example.sadiker.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

  AuthenticationProvider  authenticationProvider;
  JwtAuthenticationFilter jwtAuthenticationFilter;
    public SecurityConfig(AuthenticationProvider authenticationProvider,
    @Lazy JwtAuthenticationFilter jwtAuthenticationFilter) {
      this.authenticationProvider = authenticationProvider;
      this.jwtAuthenticationFilter = jwtAuthenticationFilter;
   
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      
      return httpSecurity.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .antMatchers("/register/**","/login/**","/v2/api-docs","/fortrying")
            .permitAll()
            .antMatchers("/userlist").hasAnyAuthority("ADMIN")
            .antMatchers("/user/me").hasAnyAuthority("ADMIN","USER")
            .anyRequest().authenticated()
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
   

    }
   
