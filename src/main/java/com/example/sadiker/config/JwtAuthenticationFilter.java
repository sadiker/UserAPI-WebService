package com.example.sadiker.config;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.sadiker.models.User;
import com.example.sadiker.service.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter  extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final SecurityImp securityImp;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {   
          final String header=request.getHeader("Authorization");
          final String jwt;
          final String email;
          System.out.println("HeaderKontrol:" + header + "HeaderSon.");

          if(header==null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            System.out.println("1");
            return;
          } 
          jwt=header.substring(7);
          email=jwtService.findEmail(jwt);

          System.out.println("2");
          if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            User user= (User) securityImp.loadUserByUsername(email);
            System.out.println("3");
            if(jwtService.tokenControl(jwt,user)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken( user,null,user.getAuthorities());
               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
               System.out.println("4");
            }
          }
          System.out.println("5");
          filterChain.doFilter(request, response);
    }
    
}
