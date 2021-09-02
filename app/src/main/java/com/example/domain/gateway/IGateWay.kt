package com.example.domain.gateway

interface IGateWay<T> {
    fun query(): T
    fun store(): T
}
