package com.zhangrui.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface UiData<S : UiState, E : UiEvent> {
    val state: StateFlow<S>
    val event: Flow<E>
}