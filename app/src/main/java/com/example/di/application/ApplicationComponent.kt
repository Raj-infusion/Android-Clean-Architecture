package com.example.di.application

import dagger.Component
import com.example.data.gateway.onboarding.InitialDataGateWay
import com.example.data.local.Preferences
import com.example.di.data.DataModule
import com.example.di.presentation.PresentationComponent
import com.example.di.presentation.PresentationModule

@Component(modules = [ApplicationModule::class, DataModule::class])
interface ApplicationComponent {
    fun newPresentationComponent(module: PresentationModule): PresentationComponent
    fun inject(preferences: Preferences)
    fun inject(initialDataGateWay: InitialDataGateWay)
}
