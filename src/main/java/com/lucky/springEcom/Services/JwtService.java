package com.lucky.springEcom.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToke(String userName){

        Map<String , Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + 1000*30*60))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
