package com.aklimets.pet.util.jwt;

import com.aklimets.pet.model.interfaces.UsernameAndIdentity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

@Component
public class JwtGenerator {
    private final PrivateKey accessTokenPrivateKey;
    private final PrivateKey refreshTokenPrivateKey;
    private final String accessTokenTtl;
    private final String refreshTokenTtl;

    public JwtGenerator(@Value("${jwt.access.token.ttl}") String accessTokenTtl,
                        @Value("${jwt.refresh.token.ttl}") String refreshTokenTtl,
                        @Value("${jwt.access.private.key.path}") String accessPrivateKeyPath,
                        @Value("${jwt.refresh.private.key.path}") String refreshPrivateKeyPath,
                        JwtKeyReader jwtKeyReader) throws Exception {
        this.accessTokenTtl = accessTokenTtl;
        this.refreshTokenTtl = refreshTokenTtl;

        var accessPrivateKey = jwtKeyReader.getPrivateKey(accessPrivateKeyPath);
        var refreshPrivateKey = jwtKeyReader.getPrivateKey(refreshPrivateKeyPath);
        this.accessTokenPrivateKey = accessPrivateKey;
        this.refreshTokenPrivateKey = refreshPrivateKey;
    }

    public String generateAccessToken(UsernameAndIdentity user) {
        return generateToken(user, accessTokenPrivateKey, accessTokenTtl);
    }

    public String generateRefreshToken(UsernameAndIdentity user) {
        return generateToken(user, refreshTokenPrivateKey, refreshTokenTtl);
    }

    private String generateToken(UsernameAndIdentity user, PrivateKey key, String ttl) {
        var claims = Jwts.claims()
                .setSubject(user.getUsername())
                .setId(user.getId())
                .setExpiration(generateExpirationDate(ttl));
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.RS256, key)
                .compact();
    }

    private static Date generateExpirationDate(String ttl) {
        return new Date(new Date().getTime() + TimeUnit.HOURS.toMillis(parseInt(ttl)));
    }
}
