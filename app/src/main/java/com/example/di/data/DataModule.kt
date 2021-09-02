package com.example.di.data

import android.app.Application
import dagger.Module
import dagger.Provides
import com.example.data.gateway.onboarding.ExampleGateWay
import com.example.data.gateway.onboarding.InitialDataGateWay
import com.example.data.remote.NetworkService
import com.example.data.remote.common.NetworkHandler
import com.example.domain.usecase.interactor.onboarding.ExampleInteractor
import com.example.domain.usecase.interactor.onboarding.ExampleInteractorImpl
import com.example.domain.usecase.interactor.onboarding.InitialDataInteractor
import com.example.domain.usecase.interactor.onboarding.InitialDataInteractorImpl
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    fun getExampleInteractor(): ExampleInteractor {
        return ExampleInteractorImpl(ExampleGateWay())
    }

    @Provides
    fun getNetworkHandler(application : Application) : NetworkHandler{
        return NetworkHandler(application)

    }

    @Provides
    fun getNetworkService(retrofit: Retrofit) : NetworkService{
        return NetworkService(retrofit)
    }

    @Provides
    fun getInitialDataInteractor(): InitialDataInteractor {
        return InitialDataInteractorImpl(InitialDataGateWay())
    }
}
