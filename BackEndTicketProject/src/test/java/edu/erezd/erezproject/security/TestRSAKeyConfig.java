package edu.erezd.erezproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class TestRSAKeyConfig {

    @Bean
    public RSAPublicKey publicKey() throws NoSuchAlgorithmException {
        return (RSAPublicKey) generateKeyPair().getPublic();
    }

    @Bean
    public RSAPrivateKey privateKey() throws NoSuchAlgorithmException {
        return (RSAPrivateKey) generateKeyPair().getPrivate();
    }

    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }
}
