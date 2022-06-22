package com.zhangrui.mvi

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

fun <S : UiState, E : UiEvent> ViewModel.mutableUiData(initState: S): Lazy<MutableUiData<S, E>> {
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

@MainThread
inline fun <reified VM : ViewModel> ViewModelStoreOwner.viewModel(): Lazy<VM> {
    return object : Lazy<VM> {
        private var cached: VM? = null

        override val value: VM
            get() {
                val viewModel = cached
                return viewModel ?: ViewModelProvider(this@viewModel)[VM::class.java].also {
                        cached = it
                    }
            }

        override fun isInitialized() = cached != null
    }
}