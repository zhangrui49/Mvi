package com.zhangrui.mvi

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty1

fun <S, V> LifecycleOwner.observeStateProp(
    flow: Flow<S>,
    property: KProperty1<S, V>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    flowCollector: FlowCollector<V>
) {

    lifecycleScope.launch {

        repeatOnLifecycle(state) {
            flow.map { value ->
                //找出需要的元素
                property.get(value)
            }
                //防抖,防止其他元素更新导致订阅更新
                .distinctUntilChanged()
                .collect(flowCollector)
        }
    }
}

fun <S : UiState> Flow<S>.partialCollect(
    lifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    action: Partition<S>.() -> Unit
) {
    val partition = object : Partition<S>(this, lifecycleOwner, state) {}
    partition.action()
}

fun <S, V> S.property(kp1: KProperty1<S, V>): V {
    return kp1.get(this)
}