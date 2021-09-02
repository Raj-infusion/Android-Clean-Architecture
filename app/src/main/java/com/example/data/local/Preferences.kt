package com.example.data.local

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.ExampleApplication
import com.example.data.framework.encryption.EncryptionFactory
import com.example.domain.entities.ExampleUserEntity
import javax.inject.Inject

class Preferences {

    companion object{
        const val USER_ID = "USER_ID"
        const val USER_NAME = "USER_NAME"
        const val ENCRYPTION_ALIAS = "SOME_ALIAS"
        const val APP_PREFS = "APP_PREFS"
    }

    private val mPrefs : SharedPreferences

    @Inject
    lateinit var mApplication: Application

    @Inject
    lateinit var mEncryptionFactory : EncryptionFactory

    init {
        ExampleApplication.mApplicationComponent.inject(this)
        mPrefs = mApplication.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }

    fun storeUserEntity(userEntity : ExampleUserEntity){
        val encryptionService = mEncryptionFactory.newAsymmetricEncryption(mApplication, ENCRYPTION_ALIAS)
        val encryptedUserID = encryptionService.encrypt(userEntity.mUserID)
        val encryptedUserName = encryptionService.encrypt(userEntity.mUserName)
        mPrefs.edit().putString(USER_ID,encryptedUserID).putString(USER_NAME, encryptedUserName).apply()
    }

    fun getExampleUserEntity() : ExampleUserEntity{
        val encryptionService = mEncryptionFactory.newAsymmetricEncryption(mApplication, ENCRYPTION_ALIAS)
        val decryptedUserID = encryptionService.decrypt(mPrefs.getString(USER_ID, "USER_ID"))
        val decryptedUserName = encryptionService.decrypt(mPrefs.getString(USER_NAME, "USER_NAME"))
        return ExampleUserEntity(decryptedUserID, decryptedUserName)
    }



}
