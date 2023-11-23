package com.tevology.logistica.models.services;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EncryptDecryptServiceImpl implements IEncryptDecryptService {
     
	@Autowired
	@Qualifier("jasyptStringEncryptor")
	private PooledPBEStringEncryptor pbeStringEncryptor;
	
	@Override
    public String encryptKey(String plainKey) {
    	return pbeStringEncryptor.encrypt(plainKey);
    }

	@Override
    public String decryptKey(String encryptedKey) { 
        return pbeStringEncryptor.decrypt(encryptedKey);
    }
}
