package com.aklimets.pet.crypto.provider;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface VersionedKeyPairProvider extends KeyPairProvider {

    PublicKey getPublicKey(String version);

    PrivateKey getPrivateKey(String version);

}
