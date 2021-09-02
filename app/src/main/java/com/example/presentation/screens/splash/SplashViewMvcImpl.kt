package com.example.presentation.screens.splash

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.R
import com.example.presentation.common.views.BaseViewMvc

class SplashViewMvcImpl(inflater: LayoutInflater, @Nullable parent: ViewGroup?) : BaseViewMvc(), SplashViewMvc {

    companion object {
        private const val GIF_URL_STRING = "file:///android_asset/gifs/Logo.gif"
    }

    private val mLogoIv: AppCompatImageView

    init {
        setRootView(inflater.inflate(R.layout.screen_splash, parent, false))
        mLogoIv = findViewById(R.id.logo_iv)
    }

    override fun startLogoAnimation() {
        val context = getContext()
        if (context != null) {
            Glide.with(context)
                .load(Uri.parse(GIF_URL_STRING))
                .into(mLogoIv)
        }
    }
}
