package com.example.domain.usecase.interactor.onboarding

import com.example.domain.common.Either
import com.example.domain.common.Failure
import com.example.domain.entities.MetaDataEntity
import com.example.domain.entities.MetaDataParam


interface InitialDataInteractor {

    suspend fun getMetaData(metaDataParam: MetaDataParam) : Either<Failure, MetaDataEntity>
}
