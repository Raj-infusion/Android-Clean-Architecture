package com.example.di.presentation

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import com.example.presentation.common.ViewMvcFactory

@Module
class PresentationModule(appCompatActivity: AppCompatActivity) {

    private val mActivity: AppCompatActivity = appCompatActivity

    @Provides
    fun getFragmentManager(): FragmentManager {
        return mActivity.supportFragmentManager
    }

    @Provides
    fun getLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(mActivity)
    }

    @Provides
    fun getViewMVCFactory(inflater: LayoutInflater): ViewMvcFactory {
        return ViewMvcFactory(inflater)
    }
}
