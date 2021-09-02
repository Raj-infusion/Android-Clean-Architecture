package com.example.presentation.common.utils

import android.util.Log
import com.example.BuildConfig

object Logger {

    fun d(message: String, tag: String = "Example App") {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }

    fun e(message: String, tag: String = "Example App") {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }

    fun i(message: String, tag: String = "Example App") {
        if (BuildConfig.DEBUG) {
            Log.i(tag, message)
        }
    }


}
