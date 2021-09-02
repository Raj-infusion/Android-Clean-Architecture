package com.example.data.framework.encryption;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.data.framework.encryption.cipher.AsymmetricCipherWrapper;
import com.example.data.framework.encryption.cipher.CipherWrapper;
import com.example.data.framework.encryption.cipher.MAsymetricCipherWrapper;
import com.example.data.framework.encryption.keystore.AsymetricKSWrapper;
import com.example.data.framework.encryption.keystore.KeyStoreWrapper;
import com.example.data.framework.encryption.keystore.MAsynetricKSWrapper;

/**
 * A Factory class to generate Encryption Services instances.
 */

public class EncryptionFactory {

    public EncryptionFactory() {

    }

    private final static String ANDROID_M_MIGRATION_KEY = "ANDROID_M_MIGRATION_KEY";
    private final static Object mMigrationLock = new Object();

    /**
     * Returns a {@link EncryptionService} that uses thr OS Keystore to generate its keys.
     *
     * @param context application context
     * @param alias   The Alias that will be associated with the generated KeyStore.
     * @return {@link EncryptionService} to be used for encrypting and decrypting Strings.
     */
    public EncryptionService newAsymmetricEncryption(@NonNull Context context, @NonNull String alias) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            synchronized (mMigrationLock) {
                return newMService(context, ANDROID_M_MIGRATION_KEY + alias);
            }
        }
        return newPreMService(context, alias);
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private MEncryptionService newMService(Context context, String alias) {
        KeyStoreWrapper keyStoreWrapper = new MAsynetricKSWrapper(context);
        CipherWrapper cipherWrapper = new MAsymetricCipherWrapper();
        return new MEncryptionService(keyStoreWrapper, cipherWrapper, alias);
    }

    private PreMEncryptionService newPreMService(Context context, String alias) {
        KeyStoreWrapper keyStoreWrapper = new AsymetricKSWrapper(context);
        CipherWrapper cipherWrapper = new AsymmetricCipherWrapper();
        return new PreMEncryptionService(keyStoreWrapper, cipherWrapper, alias);
    }

}
