package com.example.data.framework.encryption.cipher;


import java.security.Key;

public abstract class CipherWrapper {

    public abstract String encrypt(String toEncrypt, Key key);

    public abstract String decrypt(String toDecrypt, Key key);


}
