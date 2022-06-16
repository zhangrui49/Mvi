package com.zhangrui.mvi

interface IMutableUiData<S : UiState, E : UiEvent> : IUiData<S, E> {

    fun updateUiState(update: S.() -> S)

    fun sendEvent(event: E)
}