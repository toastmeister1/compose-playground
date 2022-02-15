package com.joseph.composeplayground.ui.home.dto

import com.joseph.composeplayground.base.mvi.dto.UiState
import com.joseph.composeplayground.model.Movie
import com.joseph.composeplayground.ui.common.dto.MovieListState
import com.joseph.composeplayground.util.LoadState


data class HomeState(
    val upComingMoviesState: MovieListState,
    val popularMoviesState: MovieListState
) : UiState {

    companion object {
        fun getInitial(): HomeState {
            return HomeState(
                upComingMoviesState = MovieListState.getInitial(),
                popularMoviesState = MovieListState.getInitial()
            )
        }
    }

}