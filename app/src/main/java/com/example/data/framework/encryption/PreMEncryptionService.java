package com.example.data.framework.encryption;

import androidx.annotation.NonNull;
import com.example.data.framework.encryption.cipher.CipherWrapper;
import com.example.data.framework.encryption.keystore.KeyStoreWrapper;

import java.security.KeyPair;

public class PreMEncryptionService extends EncryptionService {

    /**
     * @param keyStoreWrapper A {@link KeyStoreWrapper} instance.
     * @param cipherWrapper   A {@link CipherWrapper} instance.
     * @param alias           The Alias to be associated with the KeyStore.
     */
    PreMEncryptionService(@NonNull KeyStoreWrapper keyStoreWrapper, @NonNull CipherWrapper cipherWrapper, @NonNull String alias) {
        super(keyStoreWrapper, cipherWrapper, alias);
    }

    /**
     * Encrypts the String
     *
     * @param toEncrypt The String to Encrypt - The string must not exceed 256 chars.
     * @return an Encrypted String or Null if an error took place.
     */
    @Override
    public String encrypt(String toEncrypt) {
        KeyPair keyPair = mKeyStoreWrapper.getAndroidKeyStoreKeyPair(mAlias);
        if (keyPair != null) {
            return mCipherWrapper.encrypt(toEncrypt, keyPair.getPublic());
        }
        return null;
    }

    /**
     * Decrypts the String
     *
     * @param toDecrypt The string to decrypt
     * @return a Decrypted String ot Null if an error took place.
     */
    @Override
    public String decrypt(String toDecrypt) {
        KeyPair keyPair = mKeyStoreWrapper.getAndroidKeyStoreKeyPair(mAlias);
        if (keyPair != null) {
            return mCipherWrapper.decrypt(toDecrypt, keyPair.getPrivate());
        }
        return null;
    }
}
