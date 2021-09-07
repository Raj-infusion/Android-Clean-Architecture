package com.example.presentation.common.dialogs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.example.R
import com.example.presentation.common.views.BaseViewMvc

class ProgressBarDialogMvcImpl(inflater: LayoutInflater, @Nullable parent: ViewGroup?) :
    BaseViewMvc(), ProgressBarDialogMvc {

    init {
        setRootView(inflater.inflate(R.layout.progress_bar_dialog, parent, false))
    }
}
