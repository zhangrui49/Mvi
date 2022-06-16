package com.zhangrui.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface IUiData<S : UiState, E : UiEvent> {
    val uiState: StateFlow<S>
    val uiEvent: Flow<E>
}