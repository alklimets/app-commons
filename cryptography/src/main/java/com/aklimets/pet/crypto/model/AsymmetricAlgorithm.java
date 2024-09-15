package com.aklimets.pet.crypto.model;

public enum AsymmetricAlgorithm {
    RSA("RSA", 2048),
    DSA("DSA", 1024),
    DH("DH", 1024),
    EC("EC", 256);

    private final String algorithm;

    private final int keySize;

    AsymmetricAlgorithm(String algorithm, int keySize) {
        this.algorithm = algorithm;
        this.keySize = keySize;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public int getKeySize() {
        return keySize;
    }
}
