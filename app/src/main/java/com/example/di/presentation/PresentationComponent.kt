package com.example.di.presentation

import com.example.presentation.common.dialogs.ProgressBarDialogFragment
import dagger.Subcomponent
import com.example.presentation.screens.example.ExampleActivity
import com.example.presentation.screens.splash.SplashActivity

@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(splashActivity: SplashActivity)
    fun inject(exampleActivity: ExampleActivity)
    fun inject(progressBarDialogFragment: ProgressBarDialogFragment)
}
