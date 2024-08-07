package com.aklimets.pet.jwt.util;

import com.aklimets.pet.buildingblock.interfaces.UsernameAndIdentity;
import com.aklimets.pet.model.attribute.AccessToken;
import com.aklimets.pet.model.attribute.RefreshToken;
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

    public AccessToken generateAccessToken(UsernameAndIdentity user) {
        return new AccessToken(generateToken(user, accessTokenPrivateKey, accessTokenTtl));
    }

    public RefreshToken generateRefreshToken(UsernameAndIdentity user) {
        return new RefreshToken(generateToken(user, refreshTokenPrivateKey, refreshTokenTtl));
    }

    private String generateToken(UsernameAndIdentity user, PrivateKey key, String ttl) {
        var claims = Jwts.claims()
                .setSubject(user.getUsername().getValue())
                .setId(user.getId().getValue())
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
