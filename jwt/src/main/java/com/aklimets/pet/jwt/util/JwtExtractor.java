package com.aklimets.pet.jwt.util;

import com.aklimets.pet.buildingblock.interfaces.DomainAttribute;
import com.aklimets.pet.jwt.model.JwtUser;
import com.aklimets.pet.jwt.model.attribute.AccessToken;
import com.aklimets.pet.jwt.model.attribute.RefreshToken;
import com.aklimets.pet.jwt.model.attribute.Role;
import com.aklimets.pet.crypto.provider.VersionedKeyPairProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.PublicKey;
import java.util.function.Function;

public class JwtExtractor {

    private final VersionedKeyPairProvider accessKeyPairProvider;

    private final VersionedKeyPairProvider refreshKeyPairProvider;

    public JwtExtractor(VersionedKeyPairProvider accessKeyPairProvider, VersionedKeyPairProvider refreshKeyPairProvider) {
        this.accessKeyPairProvider = accessKeyPairProvider;
        this.refreshKeyPairProvider = refreshKeyPairProvider;
    }

    public JwtUser extractAccessJwtUser(AccessToken token) {
        var claims = extractAccessClaims(token.getValue());
        return buildJwtUser(claims);
    }

    public JwtUser extractRefreshJwtUser(RefreshToken token) {
        var claims = extractRefreshClaims(token.getValue());
        return buildJwtUser(claims);
    }

    private JwtUser buildJwtUser(Claims claims) {
        var id = extractClaim(claims, Claims::getId);
        var username = extractClaim(claims, Claims::getSubject);
        var expiration = extractClaim(claims, Claims::getExpiration);
        var role = Role.valueOf((String) claims.get("role"));
        return new JwtUser(wrap(id), wrap(username), wrap(expiration), role);
    }

    private <T> DomainAttribute<T> wrap(T value) {
        return new DomainAttribute<>() {
            @Override
            public T getValue() {
                return value;
            }
        };
    }

    private <T> T extractClaim(Claims claims, Function<Claims, T> extract) {
        return extract.apply(claims);
    }

    private Claims extractAccessClaims(String token) {
        return extractTokenClaims(token, accessKeyPairProvider.getPublicKey());
    }

    private Claims extractRefreshClaims(String token) {
        return extractTokenClaims(token, refreshKeyPairProvider.getPublicKey());
    }

    private Claims extractTokenClaims(String token, PublicKey publicKey) {
        return Jwts.parser().verifyWith(publicKey).build().parseSignedClaims(token).getPayload();
    }
}
