package com.joseph.composeplayground.ui.main

import com.joseph.composeplayground.base.component.UiAction
import com.joseph.composeplayground.base.component.UiEvent
import com.joseph.composeplayground.model.Movie


sealed class MainAction : UiAction {
    object OnUpComingRvTouchedBottom : MainAction()
}
