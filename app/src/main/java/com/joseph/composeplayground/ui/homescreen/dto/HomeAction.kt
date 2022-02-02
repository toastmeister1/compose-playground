package com.joseph.composeplayground.ui.homescreen.dto

import com.joseph.composeplayground.base.mvi.dto.UiAction
import com.joseph.composeplayground.model.Movie


sealed class HomeAction : UiAction {
    class FetchUpComingMovieList(val fetchedList: List<Movie>): HomeAction()
}