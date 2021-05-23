package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mapper.CredentialMapper;
import com.udacity.cloudstorage.model.CredentialsModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CredentialService {
   private final CredentialMapper credentialMapper;
   private final EncryptionService encryptionService;

    private final long smallest = 1000_0000_0000_0000L;
    private final long biggest =  9999_9999_9999_9999L;
    private long random;
    private String encryptedPass;
    // return a long between smallest and biggest (+1 to include biggest as well with the upper bound)
    //private long random = generateRandomNumber(biggest, smallest);

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<CredentialsModel> getCredentials(Integer userId){
        return credentialMapper.getCredentials(userId);
    }

    public Integer addCredential(CredentialsModel credential){
        random = generateRandomNumber(biggest, smallest);
        encryptedPass = encryptionService.encryptValue(credential.getPassword(), String.valueOf(random));
        credential.setKey(String.valueOf(random));
        credential.setPassword(encryptedPass);
        return credentialMapper.insertCredential(credential);
    }

    public Integer deleteCredential(Integer credentialId){
        return credentialMapper.deleteCredential(credentialId);
    }


    public Integer updateCredential(CredentialsModel credential){
        random = generateRandomNumber(biggest, smallest);
        encryptedPass = encryptionService.encryptValue(credential.getPassword(), String.valueOf(random));
        credential.setKey(String.valueOf(random));
        credential.setPassword(encryptedPass);
        return credentialMapper.updateCredential(credential);
    }

    private final Long generateRandomNumber(Long biggest, Long smallest){

        return ThreadLocalRandom.current().nextLong(smallest, biggest+1);
    }



}
