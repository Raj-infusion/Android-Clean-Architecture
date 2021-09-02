package com.example.data.framework.encryption.cipher;

import android.text.TextUtils;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.Key;


public class MAsymetricCipherWrapper extends CipherWrapper {

    private Cipher mEncryptCipher;
    private Cipher mDecryptCipher;
    private final Object mEncryptLock = new Object();
    private final Object mDecryptLock = new Object();

    private static final String TRANSFORMATION = "AES/CBC/PKCS7Padding";
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");


    public MAsymetricCipherWrapper() {
        try {
            mEncryptCipher = Cipher.getInstance(TRANSFORMATION);
            mDecryptCipher = Cipher.getInstance(TRANSFORMATION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String encrypt(String toEncrypt, Key key) {
        synchronized (mEncryptLock) {
            try {
                if (!TextUtils.isEmpty(toEncrypt)) {
                    byte[] encryptedData = encryptString(key, toEncrypt);
                    return Base64.encodeToString(encryptedData, Base64.DEFAULT);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public String decrypt(String toDecrypt, Key key) {
        synchronized (mDecryptLock) {
            try {
                if (!TextUtils.isEmpty(toDecrypt)) {
                    byte[] encryptedBytes = Base64.decode(toDecrypt, Base64.DEFAULT);
                    return decryptBytes(key, encryptedBytes);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private byte[] encryptString(Key key, String value) {
        try {
            mEncryptCipher.init(Cipher.ENCRYPT_MODE, key);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // write initialization vector to the beginning of the stream
            byte[] iv = mEncryptCipher.getIV();
            outputStream.write(iv, 0, iv.length);
            // encrypt the value using a CipherOutputStream
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, mEncryptCipher);
            cipherOutputStream.write(value.getBytes(DEFAULT_CHARSET));
            cipherOutputStream.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[256];
    }

    private String decryptBytes(Key key, byte[] bytes) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            // read the initialization vector from the beginning of the stream
            IvParameterSpec ivParams = readIvFromStream(inputStream);
            mDecryptCipher.init(Cipher.DECRYPT_MODE, key, ivParams);
            // decrypt the bytes using a CipherInputStream
            CipherInputStream cipherInputStream = new CipherInputStream(
                    inputStream, mDecryptCipher);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int n = cipherInputStream.read(buffer, 0, buffer.length);
                if (n <= 0) {
                    break;
                }
                output.write(buffer, 0, n);
            }
            return new String(output.toByteArray(), DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static IvParameterSpec readIvFromStream(ByteArrayInputStream inputStream) {
        byte[] iv = new byte[16];
        inputStream.read(iv, 0, iv.length);
        return new IvParameterSpec(iv);
    }

}
