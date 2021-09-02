package com.example.data.framework.encryption;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.example.data.framework.encryption.cipher.CipherWrapper;
import com.example.data.framework.encryption.keystore.KeyStoreWrapper;


import java.security.Key;

@RequiresApi(Build.VERSION_CODES.M)
public class MEncryptionService extends EncryptionService {

    /**
     * @param keyStoreWrapper A {@link KeyStoreWrapper} instance.
     * @param cipherWrapper   A {@link CipherWrapper} instance.
     * @param alias           The Alias to be associated with the KeyStore.
     */
    MEncryptionService(@NonNull KeyStoreWrapper keyStoreWrapper, @NonNull CipherWrapper cipherWrapper, @NonNull String alias) {
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
        Key key = mKeyStoreWrapper.getAndroidKeyStoreKey(mAlias);
        if (key != null) {
            return mCipherWrapper.encrypt(toEncrypt, key);
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
        Key key = mKeyStoreWrapper.getAndroidKeyStoreKey(mAlias);
        if (key != null) {
            return mCipherWrapper.decrypt(toDecrypt, key);
        }
        return null;
    }
}
