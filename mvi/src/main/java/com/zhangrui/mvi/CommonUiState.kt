package com.zhangrui.mvi

import android.widget.Toast

open class CommonUiState(
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
) : UiState