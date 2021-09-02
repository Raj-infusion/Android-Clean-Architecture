package com.example.data.framework.encryption.cipher;

import android.util.Base64;

import javax.crypto.Cipher;
import java.security.Key;

public class AsymmetricCipherWrapper extends CipherWrapper {


    private Cipher mEncryptCipher;
    private Cipher mDecryptCipher;
    private final Object mEncryptLock = new Object();
    private final Object mDecryptLock = new Object();

    private final static String BASE_64_SEPARATOR = "==";
    private static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    private static final String CHARSET = "UTF-8";


    public AsymmetricCipherWrapper() {

        try {
            mEncryptCipher = Cipher.getInstance(TRANSFORMATION);
            mDecryptCipher = Cipher.getInstance(TRANSFORMATION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypts the data using the supplied {@link CipherWrapper}
     *
     * @param toEncrypt The String to Encrypt.
     * @param key       The Key to encrypt the String with.
     * @return an Encrypted String.
     */
    public String encrypt(String toEncrypt, Key key) {
        synchronized (mEncryptLock) {
            try {
                if (toEncrypt.length() > 240) {
                    return encryptLongString(toEncrypt, key);
                }
                return encryptShortString(toEncrypt, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Decrypts the data using the supplied {@link CipherWrapper}
     *
     * @param toDecrypt The String to Decrypt.
     * @param key       The key that was used to encrypt the supplied String
     * @return a decrypted String.
     */
    public String decrypt(String toDecrypt, Key key) {
        synchronized (mDecryptLock) {
            try {
                if (isLongEncryptedString(toDecrypt)) {
                    return decryptLongString(toDecrypt, key);
                } else {
                    return decryptShortString(toDecrypt, key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String encryptLongString(String longString, Key key) throws Exception {
        StringBuilder encryptedResult = new StringBuilder(500);
        int lengthSeparator = 200;
        int chunks = (longString.length() / lengthSeparator) + 1;
        for (int i = 0; i < chunks; i++) {
            if (i + 1 < chunks) {
                encryptedResult.append(encryptShortString(longString.substring(i * lengthSeparator, ((i + 1) * lengthSeparator)), key));
            } else {
                encryptedResult.append(encryptShortString(longString.substring(i * lengthSeparator, longString.length()), key));
            }
        }
        return encryptedResult.toString();
    }

    private String decryptLongString(String toDecrypt, Key key) throws Exception {
        StringBuilder decryptedString = new StringBuilder(500);
        String[] splitString = toDecrypt.split(BASE_64_SEPARATOR);
        int size = splitString.length;
        for (int i = 0; i < splitString.length; i++) {
            if (i + 1 < size) {
                decryptedString.append(decryptShortString((splitString[i] + BASE_64_SEPARATOR), key));
            }
        }
        return decryptedString.toString();
    }

    private String decryptShortString(String toDecrypt, Key key) throws Exception {
        mDecryptCipher.init(Cipher.DECRYPT_MODE, key);
        byte[] encryptedData = Base64.decode(toDecrypt, Base64.DEFAULT);
        byte[] decodedData = mDecryptCipher.doFinal(encryptedData);
        return new String(decodedData, CHARSET);
    }

    private String encryptShortString(String toEncrypt, Key key) throws Exception {
        mEncryptCipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytes = mEncryptCipher.doFinal(toEncrypt.getBytes(CHARSET));
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private boolean isLongEncryptedString(String input) {
        return input.split(BASE_64_SEPARATOR, -1).length - 1 > 1;
    }

}
