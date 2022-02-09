package com.joseph.composeplayground.ui.home.dto

import com.joseph.composeplayground.base.mvi.dto.UiState
import com.joseph.composeplayground.model.Movie
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

    data class MovieListState(
        val loadState: LoadState,
        val movies: List<Movie>,
        val page: Int,
        val endReached: Boolean
    ) : UiState {

        companion object {
            fun getInitial(): MovieListState {
                return MovieListState(
                    loadState = LoadState.Idle,
                    movies = emptyList(),
                    page = 1,
                    endReached = false
                )
            }
        }
    }

}