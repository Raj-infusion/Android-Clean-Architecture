/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.domain.common

/**
 * Represents a value of one of two possible types (a disjoint union).
 * Instances of [Either] are either an instance of [FailureResponse] or [SuccessResponse].
 * FP Convention dictates that [FailureResponse] is used for "failure"
 * and [SuccessResponse] is used for "success".
 *
 * @see FailureResponse
 * @see SuccessResponse
 */
sealed class Either<out L, out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class FailureResponse<out L>(val a: L) : Either<L, Nothing>()
    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class SuccessResponse<out R>(val b: R) : Either<Nothing, R>()

    val isSuccess get() = this is SuccessResponse<R>
    val isFailure get() = this is FailureResponse<L>

    fun <L> left(a: L) = FailureResponse(a)
    fun <R> right(b: R) = SuccessResponse(b)

    fun either(fnL: (L) -> Any, fnR: (R) -> Any): Any =
            when (this) {
                is FailureResponse -> fnL(a)
                is SuccessResponse -> fnR(b)
            }
}

// Credits to Alex Hart -> https://proandroiddev.com/kotlins-nothing-type-946de7d464fb
// Composes 2 functions
fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
        when (this) {
            is Either.FailureResponse -> Either.FailureResponse(a)
            is Either.SuccessResponse -> fn(b)
        }

fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = this.flatMap(fn.c(::right))
