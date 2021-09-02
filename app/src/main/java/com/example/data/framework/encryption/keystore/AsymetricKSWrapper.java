package com.example.data.framework.encryption.keystore;

import android.annotation.TargetApi;
import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyProperties;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.security.auth.x500.X500Principal;
import java.math.BigInteger;
import java.security.*;
import java.util.Calendar;

/**
 * KeyStoreWrapper To be used for Pre Android M.
 * For Android M and above please use {@link MAsynetricKSWrapper}
 */
@TargetApi(18)
public class AsymetricKSWrapper extends KeyStoreWrapper {

    public AsymetricKSWrapper(Context context) {
        super(context);
    }

    /**
     * Fetches an Asymmetric KeyPair. If the supplied Alias is not exist yet - it will automatically generate it.
     *
     * @param alias an Alias to be associated with the KeyPair.
     * @return a {@link KeyPair} instance.
     */
    @Override
    public KeyPair getAndroidKeyStoreKeyPair(@NonNull String alias) {
        try {
            if (!isAliasExist(alias)) {
                createAndroidKeyStoreKey(alias);
            }
            PrivateKey privateKey = (PrivateKey) mKeyStore.getKey(alias, null);
            PublicKey publicKey = mKeyStore.getCertificate(alias).getPublicKey();
            if (privateKey != null && publicKey != null) {
                return new KeyPair(publicKey, privateKey);
            } else {
                Log.e("AsymetricKSWrapper", "getAndroidKeyStoreAsymmetricKeyPair()" + (publicKey == null ? "Public key is null" : "privateKey is null"));
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Key getAndroidKeyStoreKey(@Nullable String alias) {
        return null;
    }

    /**
     * @param alias The alias to generate the KeyStore key with.
     */
    @Override
    void createAndroidKeyStoreKey(@NonNull String alias) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, ANDROID_KEYSTORE);
            initGeneratorWithKeyPairGeneratorSpec(generator, alias);
            generator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * For Pre M SDK.
     *
     * @param generator a {@link KeyPairGenerator} instance.
     * @param alias     The Alias to initialize the {@link KeyPairGenerator} with.
     */
    private void initGeneratorWithKeyPairGeneratorSpec(KeyPairGenerator generator, String alias) {
        try {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            endDate.add(Calendar.YEAR, 20);
            KeyPairGeneratorSpec.Builder builder = new KeyPairGeneratorSpec.Builder(mContext.get());
            builder.setAlias(alias)
                    .setSubject(new X500Principal("CN=" + alias))
                    .setSerialNumber(BigInteger.valueOf(1337))
                    .setKeySize(2048)
                    .setStartDate(startDate.getTime())
                    .setEndDate(endDate.getTime());
            generator.initialize(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
