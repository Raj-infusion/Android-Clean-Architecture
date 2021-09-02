package com.example.presentation.screens.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.presentation.common.ViewMvcFactory
import com.example.presentation.screens.common.controllers.BaseActivity
import com.example.presentation.screens.example.ExampleActivity
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    lateinit var mViewMvc: SplashViewMvcImpl

    @Inject
    lateinit var mViewMvcFactory: ViewMvcFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPresentationComponent().inject(this)
        mViewMvc = mViewMvcFactory.getSplashViewMvc(null)
        setContentView(mViewMvc.getRootView())
        mViewMvc.startLogoAnimation()
        Handler().postDelayed(Runnable {
            startActivity(Intent(this, ExampleActivity::class.java))
            finish()
        },2000)
    }

}
