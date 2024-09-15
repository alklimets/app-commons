package com.aklimets.pet.crypto.util;

import com.aklimets.pet.crypto.model.AsymmetricAlgorithm;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class AsymmetricKeyUtil {

    public KeyPair generateKeyPair(AsymmetricAlgorithm algorithm) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm.getAlgorithm());
        keyPairGenerator.initialize(algorithm.getKeySize());
        return keyPairGenerator.generateKeyPair();
    }

    public PublicKey getPublicKeyInstance(String encodedKey, AsymmetricAlgorithm algorithm) throws Exception {
        byte[] content = Base64.getDecoder().decode(encodedKey);
        var spec = new X509EncodedKeySpec(content);
        var kf = KeyFactory.getInstance(algorithm.getAlgorithm());
        return kf.generatePublic(spec);
    }

    public PrivateKey getPrivateKeyInstance(String encodedKey, AsymmetricAlgorithm algorithm) throws Exception {
        byte[] content = Base64.getDecoder().decode(encodedKey);
        var spec = new PKCS8EncodedKeySpec(content);
        var kf = KeyFactory.getInstance(algorithm.getAlgorithm());
        return kf.generatePrivate(spec);
    }

}
