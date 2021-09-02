package com.example.data.remote.common

import com.example.BuildConfig

object NetworkConstants {

    const val MOBILE_TYPE = "ANDROID"
    const val APP_VERSION = BuildConfig.VERSION_CODE
    const val APP_LANGUAGES  = "EN"

    // Header Keys
    const val HEADER_MOBILE_TYPE = "X-MOBILE-TYPE:$MOBILE_TYPE"
    const val HEADER_APP_VERSION = "X-MOBILE-VERSION:$APP_VERSION"
    const val HEADER_APP_LANGUAGES = "X-MOBILE-LANGUAGE:$APP_LANGUAGES"


}
