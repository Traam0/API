package com.isla.api.services;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.isla.api.dto.TokenPayload;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // @Value("${jwt.secret}")
    private static String rawKey = "e16398dcae8e0fd4eafa101da3a5ea0f56b18a7bf489292b5486221ac4f77b953a8bc87b73db3f4370d7c35791820b5dbe13a6c656cfcba43502291da73fde9c";

    public String SignToken(TokenPayload tokenPayload) throws Exception {
        Map<String, Object> claims = Map.of("id", tokenPayload.id(), "version", tokenPayload.version(), "role",
                tokenPayload.role());

        byte[] rawKeyBytes = Decoders.BASE64.decode(rawKey);
        var signingKey = Keys.hmacShaKeyFor(rawKeyBytes);

        return Jwts.builder()
                .setIssuer("com.isla").setAudience("com.isla.social")
                .setSubject("ats")
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
                .signWith(signingKey)
                .compact();
        // byte[] keyBytes = Decoders.BASE64.decode(keyraw);
        // Key key = Keys.hmacShaKeyFor(keyBytes);

        // String token = Jwts.builder()
        // .claim("role", Role.ADMIN)
        // .claim("v", "cs38sa")
        // .claim("id", 1)
        // .setExpiration(new Date(System.currentTimeMillis() + expiresIn))
        // .signWith(key)
        // .compact();
        // return token;
        // } catch (Exception e) {
        // return e.getMessage();
        // }

    }
}
