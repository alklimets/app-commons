package com.aklimets.pet.crypto.util;

import com.aklimets.pet.crypto.model.AsymmetricAlgorithm;
import com.aklimets.pet.crypto.model.dto.EncodedKeyPair;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static com.aklimets.pet.crypto.model.AsymmetricAlgorithm.RSA;

public class AsymmetricKeyUtil {

    public static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    public KeyPair generateKeyPair(AsymmetricAlgorithm algorithm) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm.getAlgorithm());
        keyPairGenerator.initialize(algorithm.getKeySize());
        return keyPairGenerator.generateKeyPair();
    }

    public EncodedKeyPair generateEncodedKeyPair(AsymmetricAlgorithm algorithm) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm.getAlgorithm());
        keyPairGenerator.initialize(algorithm.getKeySize());
        return encodedKeyPair(keyPairGenerator.generateKeyPair());
    }

    public EncodedKeyPair encodedKeyPair(KeyPair keyPair) {
        var publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        var privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        return new EncodedKeyPair(publicKey, privateKey);
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

    /**
     * RSA only
     */
    public String encrypt(String dataToEncrypt, String base64PublicKey) throws Exception {
        PublicKey publicKey = getPublicKeyInstance(base64PublicKey, RSA);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] encryptedData = cipher.doFinal(dataToEncrypt.getBytes());

        // Encode the encrypted data to Base64
       return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * RSA only
     */
    public String decrypt(String dataToDecrypt, String base64PrivateKey) throws Exception {
        PrivateKey privateKey = getPrivateKeyInstance(base64PrivateKey, RSA);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        byte[] encryptedData = Base64.getDecoder().decode(dataToDecrypt);

        // Decrypt the data
        byte[] decryptedData = cipher.doFinal(encryptedData);
        return new String(decryptedData);
    }

}
