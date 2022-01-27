package com.joseph.composeplayground.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.composeplayground.base.component.*
import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


internal abstract class BaseViewModel<S : UiState, A : UiAction, E : UiEvent>() : ViewModel() {

    private val _event = MutableEventFlow<E>()
    val event = _event.asEventFlow()

    abstract val state: Flow<S>

    protected abstract suspend fun handleUiEvent(action: A)

    fun dispatchAction(action: A) = viewModelScope.launch {
        handleUiEvent(action)
    }

    protected suspend fun <T> Flow<TaskResult<T>>.collectWithCallback(onSuccess: (T) -> Unit, onFailed: (String) -> Unit) {
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