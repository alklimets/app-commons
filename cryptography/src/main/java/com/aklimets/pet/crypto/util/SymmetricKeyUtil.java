package com.aklimets.pet.crypto.util;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.UUID;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class SymmetricKeyUtil {

    public static final String AES = "AES";
    public static final String AES_ECB_PKCS_5_PADDING = "AES/ECB/PKCS5Padding";

    public String generateSessionKey() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public String encrypt(String dataToEncrypt, String sessionKey) throws Exception {
        byte[] keyBytes = sessionKey.getBytes(StandardCharsets.UTF_8);

        Key secretKey = new SecretKeySpec(keyBytes, AES);
        Cipher cipher = Cipher.getInstance(AES_ECB_PKCS_5_PADDING);
        cipher.init(ENCRYPT_MODE, secretKey);

        byte[] encryptedData = cipher.doFinal(dataToEncrypt.getBytes());

        // Encode the encrypted data to Base64
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public String decrypt(String encryptedBase64, String sessionKey) throws Exception {
        byte[] keyBytes = sessionKey.getBytes(StandardCharsets.UTF_8);

        Key secretKey = new SecretKeySpec(keyBytes, AES);
        Cipher cipher = Cipher.getInstance(AES_ECB_PKCS_5_PADDING);
        cipher.init(DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedBase64));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
