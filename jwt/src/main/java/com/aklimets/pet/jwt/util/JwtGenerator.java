package com.aklimets.pet.jwt.util;

import com.aklimets.pet.jwt.model.JwtClaims;
import com.aklimets.pet.jwt.model.attribute.AccessToken;
import com.aklimets.pet.jwt.model.attribute.RefreshToken;
import com.aklimets.pet.crypto.provider.KeyPairProvider;
import io.jsonwebtoken.Jwts;

import java.security.PrivateKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class JwtGenerator {
    private final KeyPairProvider accessKeyPairProvider;
    private final KeyPairProvider refreshKeyPairProvider;
    private final String accessTokenTtl;
    private final String refreshTokenTtl;

    public JwtGenerator(KeyPairProvider accessKeyPairProvider, KeyPairProvider refreshKeyPairProvider, String accessTokenTtl, String refreshTokenTtl) {
        this.accessKeyPairProvider = accessKeyPairProvider;
        this.refreshKeyPairProvider = refreshKeyPairProvider;
        this.accessTokenTtl = accessTokenTtl;
        this.refreshTokenTtl = refreshTokenTtl;
    }

    public AccessToken generateAccessToken(JwtClaims user) {
        return new AccessToken(generateToken(user, accessKeyPairProvider.getPrivateKey(), accessTokenTtl));
    }

    public RefreshToken generateRefreshToken(JwtClaims user) {
        return new RefreshToken(generateToken(user, refreshKeyPairProvider.getPrivateKey(), refreshTokenTtl));
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
