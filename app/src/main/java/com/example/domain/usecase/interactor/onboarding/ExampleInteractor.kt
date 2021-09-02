package com.example.domain.usecase.interactor.onboarding

import com.example.domain.entities.ExampleUserEntity

/**
 * Interactor class - to receive events from Presentation layer.
 * The [com.example.domain.gateway.IGateWayArgumented] is injected from [com.example.di.data.DataModule]
 */
interface ExampleInteractor {
    fun getDummyUserID(): ExampleUserEntity

    fun storeDummyUser(exampleEntity: ExampleUserEntity)
}
