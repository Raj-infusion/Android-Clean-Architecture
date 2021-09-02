package com.example.domain.usecase.interactor.onboarding

import com.example.data.remote.UseCaseViewModel
import com.example.domain.common.Either
import com.example.domain.common.Failure
import com.example.domain.entities.MetaDataEntity
import com.example.domain.entities.MetaDataParam
import com.example.domain.gateway.IGateWayQuery

class InitialDataInteractorImpl(val gateWay: IGateWayQuery<Either<Failure, MetaDataEntity>, MetaDataParam>) :
    UseCaseViewModel(), InitialDataInteractor {

    override suspend fun getMetaData(metaDataParam: MetaDataParam) : Either<Failure, MetaDataEntity>{
        return gateWay.query(metaDataParam)
    }


}
