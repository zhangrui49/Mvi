package com.zhangrui.mvi

import android.widget.Toast

data class CommonUiState(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
) : UiState