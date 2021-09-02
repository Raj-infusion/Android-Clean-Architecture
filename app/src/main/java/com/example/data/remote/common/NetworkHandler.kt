package com.example.data.remote.common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class NetworkHandler constructor(private val application: Application) {

    fun isConnected() : Boolean {
        val networkInfo = (application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }

}
