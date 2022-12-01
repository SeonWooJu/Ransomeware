package com.joke.object;

import java.security.PrivateKey;
import java.security.PublicKey;

public class EncryptionKey {
    PublicKey publicKey;
    PrivateKey privateKey;

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
    
    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
    
}
