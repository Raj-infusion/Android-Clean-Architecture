package com.example.domain.gateway

interface IGateWayQuery<T, H> {
    fun query(param: H?): T
}
