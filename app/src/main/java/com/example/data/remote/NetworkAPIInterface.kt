package com.example.data.remote

import com.example.data.remote.common.NetworkConstants
import com.example.domain.entities.MetaDataEntity
import com.example.domain.entities.MetaDataParam
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NetworkAPIInterface {

    @POST("api/v1/public/metadata")
    @Headers("Content-Type:application/json",
            NetworkConstants.HEADER_MOBILE_TYPE,
            NetworkConstants.HEADER_APP_LANGUAGES,
            NetworkConstants.HEADER_APP_VERSION)
    fun getMetaData(@Body metaDataParam: MetaDataParam?) : Call<MetaDataEntity>

}
