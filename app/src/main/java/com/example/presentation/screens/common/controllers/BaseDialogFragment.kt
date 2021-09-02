package com.example.presentation.screens.common.controllers

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.ExampleApplication
import com.example.di.application.ApplicationComponent
import com.example.di.presentation.PresentationComponent
import com.example.di.presentation.PresentationModule

class BaseDialogFragment : DialogFragment() {
    var mIsInjectOrUsed = false

    private fun getApplicationComponent(): ApplicationComponent {
        return ExampleApplication.mApplicationComponent
    }

    @UiThread
    fun getPresentationComponent(): PresentationComponent {
        if (mIsInjectOrUsed) {
            throw RuntimeException("There is no need to inject more than once")
        }
        mIsInjectOrUsed = true
        return getApplicationComponent()
            .newPresentationComponent(PresentationModule(activity as AppCompatActivity))
    }
}
