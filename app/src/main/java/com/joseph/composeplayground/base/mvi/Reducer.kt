package com.joseph.composeplayground.base.mvi

import com.joseph.composeplayground.base.mvi.dto.UiAction
import com.joseph.composeplayground.base.mvi.dto.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


abstract class Reducer<S : UiState, A : UiAction>(initialState: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    suspend fun sendAction(action: A) {
        reduce(_state.value, action)
    }

    fun updateState(newState: S) {
        _state.value = newState
    }

    protected abstract suspend fun reduce(oldState: S, action: A)
}