package com.example.data.gateway.onboarding

import com.example.ExampleApplication
import com.example.data.gateway.onboarding.common.BaseGateWay
import com.example.data.remote.NetworkService
import com.example.data.remote.common.NetworkHandler
import com.example.domain.common.Either
import com.example.domain.common.Failure
import com.example.domain.entities.MetaDataEntity
import com.example.domain.entities.MetaDataParam
import com.example.domain.gateway.IGateWayQuery
import javax.inject.Inject

class InitialDataGateWay : BaseGateWay(), IGateWayQuery<Either<Failure, MetaDataEntity>, MetaDataParam> {

    @Inject
    lateinit var networkHandler: NetworkHandler

    @Inject
    lateinit var service: NetworkService

    init {
        ExampleApplication.mApplicationComponent.inject(this)
    }

    override fun query(s: MetaDataParam?): Either<Failure, MetaDataEntity> {
        return when (networkHandler.isConnected()) {
            true -> request(service.getMetaData(s), { it }, MetaDataEntity() )
            false -> Either.FailureResponse(Failure.NetworkConnection)
        }
    }
}
