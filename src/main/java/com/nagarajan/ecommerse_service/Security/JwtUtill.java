package com.nagarajan.ecommerse_service.Security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
@Component
public class JwtUtill {

    private final String secretkey="Everybody was lookking him,he may not be perfect,but he always herself";
    private final long Exp=1000*60*60*24;
    private final Key key= Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));
    public String genderateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*Exp))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractname(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean ValidateToken(String token,String email){
        try{
            extractname(token);
            return true;
        }catch(JwtException e){
            return  false;
        }
    }
}
