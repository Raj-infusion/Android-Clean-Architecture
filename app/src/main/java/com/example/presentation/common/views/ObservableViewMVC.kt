package com.example.presentation.common.views

interface ObservableViewMVC<ListenerType> : ViewMvc {

    fun registerListener(listener: ListenerType)
    fun unRegisterListeners(listener: ListenerType)
}
