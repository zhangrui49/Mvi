package com.zhangrui.mvi


open class CommonUiEvent : UiEvent {

    class ToastEvent(val msg: String, val long: Boolean = false) : CommonUiEvent()
}