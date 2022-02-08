package com.joseph.composeplayground.ui.home.dto

import com.joseph.composeplayground.base.mvi.dto.UiState
import com.joseph.composeplayground.model.Movie
import com.joseph.composeplayground.util.LoadState


data class HomeState(
    val upComingMoviesState: UpComingMoviesState
) : UiState {

    companion object {
        fun getInitial(): HomeState {
            return HomeState(
                upComingMoviesState = UpComingMoviesState.getInitial()
            )
        }
    }

    data class UpComingMoviesState(
        val loadState: LoadState,
        val movies: List<Movie>,
        val page: Int,
        val endReached: Boolean
    ) {
        fun changeLoadState(changingState: LoadState): UpComingMoviesState {
            return this.copy(loadState = changingState)
        }

        companion object {
            fun getInitial(): UpComingMoviesState {
                return UpComingMoviesState(
                    loadState = LoadState.Idle,
                    movies = emptyList(),
                    page = 1,
                    endReached = false
                )
            }
        }
    }

}