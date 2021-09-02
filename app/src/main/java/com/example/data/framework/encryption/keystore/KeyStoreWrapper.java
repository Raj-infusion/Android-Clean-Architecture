package com.example.data.framework.encryption.keystore;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.SoftReference;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;

public abstract class KeyStoreWrapper {

    KeyStore mKeyStore;
    SoftReference<Context> mContext;
    protected static final String ANDROID_KEYSTORE = "AndroidKeyStore";

    KeyStoreWrapper(Context context) {
        mKeyStore = createKeyStore();
        mContext = new SoftReference<>(context);
    }

    public abstract KeyPair getAndroidKeyStoreKeyPair(@NonNull String alias);

    public abstract Key getAndroidKeyStoreKey(@Nullable String alias);

    abstract void createAndroidKeyStoreKey(@NonNull String alias);

    /**
     * Will remove the supplied alias from the KeyStore.
     *
     * @param alias the alias to be removed.
     * @return True if removed successfully.
     */
    public boolean removeAndroidKeyStoreKey(String alias) {
        try {
            mKeyStore.deleteEntry(alias);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Will load the KeyStore instance.
     *
     * @return a {@link KeyStore} instance.
     */
    private KeyStore createKeyStore() {
        try {
            KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
            keyStore.load(null);
            return keyStore;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Checks if an Alias is already associated with the current KeyStore.
     *
     * @param alias The alias to check.
     * @return True if the KeyStore contains this alias.
     */
    boolean isAliasExist(String alias) {
        try {
            return mKeyStore.containsAlias(alias);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
