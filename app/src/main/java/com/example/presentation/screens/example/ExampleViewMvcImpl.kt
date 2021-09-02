package com.example.presentation.screens.example

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import com.example.R
import com.example.presentation.common.views.BaseObservableViewMvc

class ExampleViewMvcImpl(inflater: LayoutInflater, @Nullable parent: ViewGroup?) :
    BaseObservableViewMvc<ExampleViewMvc.Listener>(), ExampleViewMvc {

    private val mGetUserBtn: Button

    init {
        setRootView(inflater.inflate(R.layout.screen_example, parent, false))
        mGetUserBtn = findViewById(R.id.get_user_btn)
        mGetUserBtn.setOnClickListener {
            for (listener: ExampleViewMvc.Listener in mListeners) {
                listener.getUserClicked()
            }
        }
    }
}
