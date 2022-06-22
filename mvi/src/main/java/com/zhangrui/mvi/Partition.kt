package com.zhangrui.mvi

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty1

abstract class Partition<S : UiState>(
    private val flow: Flow<S>,
    private val lifecycleOwner: LifecycleOwner,
    private val state: Lifecycle.State,
) {

    fun <V> collect(
        prop1: KProperty1<S, V>,
        action: (V) -> Unit
    ) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(state) {
                flow.map { prop1.get(it) }
                    .distinctUntilChanged()
                    .collect { partialState ->
                        action(partialState)
                    }
            }
        }

    }
}