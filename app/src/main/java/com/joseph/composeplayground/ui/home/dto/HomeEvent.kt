package com.joseph.composeplayground.ui.home.dto

import com.joseph.composeplayground.base.mvi.dto.UiEvent


sealed class HomeEvent : UiEvent {
    class FetchMovieListFailed(val message: String) : HomeEvent()
}