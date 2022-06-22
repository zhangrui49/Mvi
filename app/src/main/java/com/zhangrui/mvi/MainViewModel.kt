package com.zhangrui.mvi

import androidx.lifecycle.ViewModel

class MainViewModel : UiDataViewModel<MainViewModel.MainUiState, CommonUiEvent>(MainUiState()) {

    data class MainUiState(
        val data: List<String> = emptyList(),
        val loadMore: Boolean = true
    ) : CommonUiState()


}