package com.example.domain.usecase.interactor.onboarding

import com.example.domain.entities.ExampleUserEntity
import com.example.domain.gateway.IGateWayArgumented

class ExampleInteractorImpl(gateWay: IGateWayArgumented<ExampleUserEntity, String>) : ExampleInteractor {

    private val mGateWay = gateWay

    override fun getDummyUserID(): ExampleUserEntity {
        return mGateWay.query(null)
    }

    override fun storeDummyUser(exampleEntity: ExampleUserEntity) {
        mGateWay.store(exampleEntity)
    }
}
