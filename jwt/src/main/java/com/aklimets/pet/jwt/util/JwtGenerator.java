package com.aklimets.pet.jwt.util;

import com.aklimets.pet.jwt.model.JwtClaims;
import com.aklimets.pet.jwt.model.attribute.AccessToken;
import com.aklimets.pet.jwt.model.attribute.RefreshToken;
import io.jsonwebtoken.Jwts;

import java.security.PrivateKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class JwtGenerator {
    private final PrivateKey accessTokenPrivateKey;
    private final PrivateKey refreshTokenPrivateKey;
    private final String accessTokenTtl;
    private final String refreshTokenTtl;

    public JwtGenerator(String accessTokenTtl,
                        String refreshTokenTtl,
                        String accessPrivateKeyPath,
                        String refreshPrivateKeyPath,
                        JwtKeyReader jwtKeyReader) throws Exception {
        this.accessTokenTtl = accessTokenTtl;
        this.refreshTokenTtl = refreshTokenTtl;

        var accessPrivateKey = jwtKeyReader.getPrivateKey(accessPrivateKeyPath);
        var refreshPrivateKey = jwtKeyReader.getPrivateKey(refreshPrivateKeyPath);
        this.accessTokenPrivateKey = accessPrivateKey;
        this.refreshTokenPrivateKey = refreshPrivateKey;
    }

    public AccessToken generateAccessToken(JwtClaims user) {
        return new AccessToken(generateToken(user, accessTokenPrivateKey, accessTokenTtl));
    }

    public RefreshToken generateRefreshToken(JwtClaims user) {
        return new RefreshToken(generateToken(user, refreshTokenPrivateKey, refreshTokenTtl));
    }

    private String generateToken(JwtClaims user, PrivateKey key, String ttl) {
        return Jwts.builder()
                .subject(user.getUsername().getValue())
                .id(user.getId().getValue())
                .expiration(generateExpirationDate(ttl))
                .claim("role", user.getRole())
                .signWith(key)
                .compact();
    }

    private static Date generateExpirationDate(String ttl) {
        return new Date(new Date().getTime() + TimeUnit.HOURS.toMillis(parseInt(ttl)));
    }
}
