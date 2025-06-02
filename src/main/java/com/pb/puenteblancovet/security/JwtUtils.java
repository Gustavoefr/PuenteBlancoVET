package com.puenteblanco.pb.security;

import com.puenteblanco.pb.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expirationMs;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Genera el token JWT con claims seguros
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getCorreo())
                .claim("role","ROLE_"+ user.getRole().getNombre())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //  Extrae el correo desde el token (usado como username)
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Verifica si el token es válido y no ha expirado
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token); // lanza excepción si no es válido
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Método interno para parsear el token y extraer claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
