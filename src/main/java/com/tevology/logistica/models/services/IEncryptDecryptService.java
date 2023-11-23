package com.tevology.logistica.models.services;

public interface IEncryptDecryptService {

    public String encryptKey(String plainKey);
     
    public String decryptKey(String encryptedKey);
}
