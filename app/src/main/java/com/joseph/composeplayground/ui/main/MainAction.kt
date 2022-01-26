package com.joseph.composeplayground.ui.main

import com.joseph.composeplayground.base.component.RenderAction
import com.joseph.composeplayground.base.component.State
import com.joseph.composeplayground.base.component.UserAction


sealed class MainUserAction : UserAction

sealed class MainRenderAction : RenderAction {
    class ShowToast(message: String) : MainRenderAction()
}