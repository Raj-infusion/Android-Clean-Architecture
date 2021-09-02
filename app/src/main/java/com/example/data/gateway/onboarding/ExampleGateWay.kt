package com.example.data.gateway.onboarding

import com.example.data.local.Preferences
import com.example.domain.entities.ExampleUserEntity
import com.example.domain.gateway.IGateWayArgumented

/**
 * Gateway from Domain Layer
 */
class ExampleGateWay : IGateWayArgumented<ExampleUserEntity, String> {

    override fun query(s: String?): ExampleUserEntity {
        return Preferences().getExampleUserEntity()
    }

    override fun store(o: ExampleUserEntity) {
        Preferences().storeUserEntity(o)
    }
}
