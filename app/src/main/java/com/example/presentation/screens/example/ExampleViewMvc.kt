package com.example.presentation.screens.example

import com.example.presentation.common.views.ObservableViewMVC

interface ExampleViewMvc : ObservableViewMVC<ExampleViewMvc.Listener> {

    interface Listener {
        fun getUserClicked()
    }

}
