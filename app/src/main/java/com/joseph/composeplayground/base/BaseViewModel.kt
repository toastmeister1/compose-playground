package com.joseph.composeplayground.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.composeplayground.base.component.*
import com.joseph.composeplayground.base.component.State
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


internal abstract class BaseViewModel<S : State, I : InteractAction, R : RenderAction>() : ViewModel() {

    private val _renderAction = MutableEventFlow<R>()
    val renderAction = _renderAction.asEventFlow()

    private val _state  = MutableStateFlow<S?>(null)
    val state = _state.asStateFlow()

    suspend fun dispatchAction(interactAction: I) = viewModelScope.launch {
        handleInteractAction()
    }

    suspend fun dispatchRenderAction(renderAction: R) {
        _renderAction.emit(renderAction)
    }

    abstract suspend fun handleInteractAction()
}