package com.joseph.composeplayground.base.component

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


internal abstract class Reducer<S : UiState, E : UiEvent>(initialState: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    fun sendEvent(event: E) {
        reduce(_state.value, event)
    }

    fun setState(newState: S) {
        _state.value = newState
    }

    protected abstract fun reduce(oldState: S, event: E)
}