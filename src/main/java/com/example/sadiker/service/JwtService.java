package com.example.sadiker.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.sadiker.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private String MY_KEY="sadikersadikersadikersadikersadikersadikersadikersadikersadikerr";

    public String findEmail(String jwt) {
        return exportToken(jwt,Claims::getSubject);
    }

    private <T> T exportToken(String jwt, Function<Claims,T> claimsTFunction) {
        final Claims claims=Jwts.parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(jwt).getBody();

        return claimsTFunction.apply(claims);
    }

    private Key getKey() {
        byte[] key =Decoders.BASE64.decode(MY_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean tokenControl(String jwt, User user) {
        final String email=findEmail(jwt);
        return (email.equals(user.getEmail())&& !exportToken(jwt, Claims::getExpiration).before(new Date()));
    }

    public String generateToken(User user) {
        return Jwts.builder().setClaims(new HashMap<>()).setSubject(user.getEmail()).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 10000*60*15))
        .signWith(getKey(),SignatureAlgorithm.HS256)
        .compact();
    }
    
}
