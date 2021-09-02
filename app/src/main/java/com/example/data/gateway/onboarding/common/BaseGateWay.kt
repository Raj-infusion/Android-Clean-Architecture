package com.example.data.gateway.onboarding.common

import com.example.domain.common.Either
import com.example.domain.common.Failure
import retrofit2.Call

abstract class BaseGateWay {

    fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.SuccessResponse(transform((response.body() ?: default)))
                false -> Either.FailureResponse(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.FailureResponse(Failure.ServerError)
        }
    }
}
