package com.joseph.composeplayground.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.composeplayground.base.mvi.*
import com.joseph.composeplayground.base.mvi.dto.UiAction
import com.joseph.composeplayground.base.mvi.dto.UiEvent
import com.joseph.composeplayground.base.mvi.dto.UiState
import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class BaseViewModel<S : UiState, A : UiAction, E : UiEvent>(
    initialState: S
) : ViewModel() {

    private val _uiState = MutableStateFlow<S>(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event = MutableEventFlow<E>()
    val event = _event.asEventFlow()

    protected suspend fun dispatchEvent(event: E) {
        _event.emit(event)
    }

    fun onAction(action: A) = viewModelScope.launch {
        handleAction(action)
    }

    protected fun updateState(newState: S) {
        _uiState.value = newState
    }

    protected abstract suspend fun handleAction(action: A)

    protected suspend fun <T> Flow<TaskResult<T>>.collectWithCallback(onSuccess: suspend (T) -> Unit, onFailed: suspend (String) -> Unit) {
        collect { result ->
            when(result) {
                is TaskResult.Success -> {
                    onSuccess.invoke(result.data)
                }

                is TaskResult.Failed -> {
                    onFailed.invoke(result.message)
                }
            }
        }
    }
}