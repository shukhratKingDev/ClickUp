package com.company.clickup.security;

import com.company.clickup.entity.enums.SystemRoleName;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.Date;

@Component
public class JwtProvider {
    private final String key="this is secret code for this project";
    private final long expirationDate= 1000L *60*60*24*30*12;
    public String generateJwt(String username, SystemRoleName role){
        String jwt = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationDate))
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return jwt;
    }
    public String getEmailFromToken(String token){
        String email = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return email;
    }
}
