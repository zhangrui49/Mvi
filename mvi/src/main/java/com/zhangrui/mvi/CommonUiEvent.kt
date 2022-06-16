package com.zhangrui.mvi

import android.widget.Toast

sealed class CommonUiEvent : UiEvent {

    class ToastEvent(val msg: String, val long: Boolean = false) : CommonUiEvent()
}