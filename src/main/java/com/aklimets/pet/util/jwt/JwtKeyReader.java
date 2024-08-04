package com.aklimets.pet.util.jwt;

import com.google.common.io.Resources;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

@Component
public class JwtKeyReader {

    public static final String ALGORITHM = "RSA";

    public PrivateKey getPrivateKey(String privateKeyPath) throws Exception {
        var pemObject = readPemFile(privateKeyPath);
        var content = pemObject.getContent();
        var spec = new PKCS8EncodedKeySpec(content);
        var kf = KeyFactory.getInstance(ALGORITHM);
        return kf.generatePrivate(spec);
    }

    public PublicKey getPublicKey(String publicKeyPath) throws Exception {
        var pemObject = readPemFile(publicKeyPath);
        var content = pemObject.getContent();
        var spec = new X509EncodedKeySpec(content);
        var kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    private PemObject readPemFile(String path) throws IOException {
        try (var reader =
                     new InputStreamReader(Objects.requireNonNull(JwtKeyReader.class.getClassLoader().getResourceAsStream(path)));
             var pemReader = new PemReader(reader)) {
            return pemReader.readPemObject();
        }
    }
}
