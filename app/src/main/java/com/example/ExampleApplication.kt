package com.example

import android.app.Application
import com.example.di.application.ApplicationComponent
import com.example.di.application.ApplicationModule
import com.example.di.application.DaggerApplicationComponent

class ExampleApplication : Application() {

    companion object{
        lateinit var mApplicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }

}
