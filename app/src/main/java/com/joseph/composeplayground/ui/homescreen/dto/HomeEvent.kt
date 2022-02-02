package com.joseph.composeplayground.ui.homescreen.dto

import com.joseph.composeplayground.base.mvi.dto.UiEvent


sealed class HomeEvent : UiEvent {
    class FetchMovieListFailed(val message: String) : HomeEvent()
}