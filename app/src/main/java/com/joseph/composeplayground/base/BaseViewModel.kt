package com.joseph.composeplayground.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joseph.composeplayground.base.component.*
import com.joseph.composeplayground.base.component.State
import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


internal abstract class BaseViewModel<S : State, I : UserAction, R : RenderAction>() : ViewModel() {

    protected val _renderAction = MutableEventFlow<R>()
    val renderAction = _renderAction.asEventFlow()

    protected val _state  = MutableStateFlow<S?>(null)
    val state = _state.asStateFlow()

    fun dispatchInteractAction(interactAction: I) = viewModelScope.launch {
        handleInteractAction()
    }

    protected fun dispatchRenderAction(renderAction: R) = viewModelScope.launch {
        _renderAction.emit(renderAction)
    }

    protected abstract suspend fun handleInteractAction()

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