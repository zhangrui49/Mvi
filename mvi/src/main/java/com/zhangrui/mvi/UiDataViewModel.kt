package com.zhangrui.mvi

import androidx.lifecycle.ViewModel

abstract class UiDataViewModel<S : UiState, E : UiEvent>(initState: S) : ViewModel() {

    //private val _uiData = MutableUiData<S, E>(initState, viewModelScope)
    private val _uiData by mutableUiData<S, E>(initState)

    val uiData: UiData<S, E>
        get() = _uiData


}