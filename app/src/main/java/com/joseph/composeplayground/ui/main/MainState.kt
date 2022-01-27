package com.joseph.composeplayground.ui.main

import com.joseph.composeplayground.base.component.UiState
import com.joseph.composeplayground.model.Movie


internal data class MainState(
    val ioState: MainState.IOState,
    val upComingMovieList: List<Movie>
) : UiState {

    data class IOState(
        val isLoading: Boolean
    ) {
        companion object {
            fun getInitial(): IOState {
                return IOState(isLoading = false)
            }
        }
    }

    companion object {
        fun getInitial(): MainState {
            return MainState(
                ioState = IOState.getInitial(),
                upComingMovieList = emptyList()
            )
        }
    }
}