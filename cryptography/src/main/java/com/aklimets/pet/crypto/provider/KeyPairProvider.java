package com.aklimets.pet.crypto.provider;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface KeyPairProvider {

    PublicKey getPublicKey();

    PrivateKey getPrivateKey();
}
