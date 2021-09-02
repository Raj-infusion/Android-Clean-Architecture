package com.example.data.remote

import com.example.domain.entities.MetaDataEntity
import com.example.domain.entities.MetaDataParam
import retrofit2.Call
import retrofit2.Retrofit


class NetworkService constructor(retrofit: Retrofit) : NetworkAPIInterface {

    private val networkAPI by lazy { retrofit.create(NetworkAPIInterface::class.java) }

    override fun getMetaData(metaDataParam: MetaDataParam?): Call<MetaDataEntity> = networkAPI.getMetaData(metaDataParam)







}
