package com.example.data.framework.encryption;


import androidx.annotation.NonNull;
import com.example.data.framework.encryption.cipher.CipherWrapper;
import com.example.data.framework.encryption.keystore.KeyStoreWrapper;

/**
 * Responsible for Encrypting and Decrypting Strings.
 */

public abstract class EncryptionService {

    String mAlias;
    KeyStoreWrapper mKeyStoreWrapper;
    CipherWrapper mCipherWrapper;

    /**
     * @param keyStoreWrapper A {@link KeyStoreWrapper} instance.
     * @param cipherWrapper   A {@link CipherWrapper} instance.
     * @param alias           The Alias to be associated with the KeyStore.
     */
    EncryptionService(@NonNull KeyStoreWrapper keyStoreWrapper, @NonNull CipherWrapper cipherWrapper, @NonNull String alias) {
        mAlias = alias;
        mKeyStoreWrapper = keyStoreWrapper;
        mCipherWrapper = cipherWrapper;
    }

    /**
     * Will remove the Alias supplied in constructor from the KeyStore.
     */
    public void removeKey() {
        mKeyStoreWrapper.removeAndroidKeyStoreKey(mAlias);
    }

    public abstract String encrypt(String toEncrypt);

    public abstract String decrypt(String toDecrypt);
}
