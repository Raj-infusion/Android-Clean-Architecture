package com.example.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.presentation.common.dialogs.ProgressBarDialogMvc
import com.example.presentation.common.dialogs.ProgressBarDialogMvcImpl
import com.example.presentation.screens.example.ExampleViewMvc
import com.example.presentation.screens.example.ExampleViewMvcImpl
import com.example.presentation.screens.splash.SplashViewMvcImpl

class ViewMvcFactory(layoutInflater : LayoutInflater) {

    private val mInflater = layoutInflater

    fun getSplashViewMvc(root : ViewGroup?) : SplashViewMvcImpl {
        return SplashViewMvcImpl(mInflater, root)
    }

    fun getExampleViewMvc(root : ViewGroup?) : ExampleViewMvc{
        return ExampleViewMvcImpl(mInflater, root)
    }

    fun getProgressDialogViewMvc(root: ViewGroup?): ProgressBarDialogMvc {
        return ProgressBarDialogMvcImpl(mInflater, root)
    }

}
