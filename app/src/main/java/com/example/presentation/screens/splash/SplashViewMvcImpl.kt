package com.example.presentation.screens.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.example.R
import com.example.presentation.common.views.BaseViewMvc

class SplashViewMvcImpl(inflater: LayoutInflater, @Nullable parent: ViewGroup?) : BaseViewMvc(),
    SplashViewMvc {


    private val mLogoIv: AppCompatImageView

    init {
        setRootView(inflater.inflate(R.layout.screen_splash, parent, false))
        mLogoIv = findViewById(R.id.logo_iv)
    }

    override fun startLogoAnimation() {
        val context = getContext()
        if (context != null) {
            Glide.with(context)
                .load(R.drawable.ic_launcher_background)
                .into(mLogoIv)
        }
    }
}
