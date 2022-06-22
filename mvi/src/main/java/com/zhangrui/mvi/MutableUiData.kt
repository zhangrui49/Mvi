package com.zhangrui.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MutableUiData<S : UiState, E : UiEvent>(initState: S, private val scope: CoroutineScope) :
    IMutableUiData<S, E> {

    private val _uiState = MutableStateFlow(initState)
    private val _uiEvent = MutableSharedFlow<E>()

    override val state: StateFlow<S>
        get() = _uiState.asStateFlow()

    override val event: Flow<E>
        get() = _uiEvent

    override fun updateUiState(update: S.() -> S) {
        _uiState.update {
            update(_uiState.value)
        }
    }

    override fun sendEvent(event: E) {
        scope.launch {
            _uiEvent.emit(event)
        }
    }
}