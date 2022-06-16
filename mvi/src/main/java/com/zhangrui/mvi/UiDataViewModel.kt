package com.zhangrui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

abstract class UiDataViewModel<S : UiState, E : UiEvent>(initState: S) : ViewModel() {

    private val _uiData = MutableUiData<S, E>(initState, viewModelScope)
    //private val _uiData by uiData<S, E>(initState)

    val uiData: IUiData<S, E>
        get() = _uiData


}