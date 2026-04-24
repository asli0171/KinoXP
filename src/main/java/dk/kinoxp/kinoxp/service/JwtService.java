package dk.kinoxp.kinoxp.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken() {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.builder()
                .subject("kinoxp-main-app")
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60000)) // 1 minut
                .signWith(key)
                .compact();
    }
}
