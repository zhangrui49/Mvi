package com.zhangrui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

fun <S : UiState, E : UiEvent> ViewModel.uiData(initState: S): Lazy<MutableUiData<S, E>> {
    return object : Lazy<MutableUiData<S, E>> {

        private var cached: MutableUiData<S, E>? = null

        override val value: MutableUiData<S, E>
            get() {
                val uiData = cached
                return uiData ?: MutableUiData<S, E>(initState, viewModelScope).also {
                    cached = it
                }
            }

        override fun isInitialized(): Boolean {
            return cached != null
        }

    }
}