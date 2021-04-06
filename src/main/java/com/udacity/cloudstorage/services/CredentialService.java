package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mapper.CredentialMapper;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class CredentialService {
   private final CredentialMapper credentialMapper;
   private final EncryptionService encryptionService;

    long smallest = 1000_0000_0000_0000L;
    long biggest =  9999_9999_9999_9999L;

    // return a long between smallest and biggest (+1 to include biggest as well with the upper bound)
    long random = generateRandomNumber(biggest, smallest);

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    private final Long generateRandomNumber(Long biggest, Long smallest){

        return ThreadLocalRandom.current().nextLong(smallest, biggest+1);
    }



}
