package com.example.data.framework.encryption.keystore;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * KeyStoreWrapper To be used for Android M and above.
 * For Pre Android M please use {@link AsymetricKSWrapper}
 */
@TargetApi(Build.VERSION_CODES.M)
public class MAsynetricKSWrapper extends KeyStoreWrapper {

    public MAsynetricKSWrapper(Context context) {
        super(context);
    }

    @Override
    public KeyPair getAndroidKeyStoreKeyPair(@NonNull String alias) {
        return null;
    }

    @Override
    public Key getAndroidKeyStoreKey(@Nullable String alias) {
        try {
            if (!isAliasExist(alias)) {
                createAndroidKeyStoreKey(alias);
            }
            return mKeyStore.getKey(alias, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    void createAndroidKeyStoreKey(@NonNull String alias) {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE);
            initGeneratorWithKeyGenParameterSpec(generator, alias);
            generator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * For M SDK.
     *
     * @param generator a {@link KeyPairGenerator} instance.
     * @param alias     The Alias to initialize the {@link KeyGenerator} with.
     */
    private void initGeneratorWithKeyGenParameterSpec(KeyGenerator generator, String alias) {
        try {
            KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_DECRYPT | KeyProperties.PURPOSE_ENCRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .setRandomizedEncryptionRequired(true)
                    .setKeySize(256);
            generator.init(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
