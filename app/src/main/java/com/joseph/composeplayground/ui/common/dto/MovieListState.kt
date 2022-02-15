package com.joseph.composeplayground.ui.common.dto

import com.joseph.composeplayground.base.mvi.dto.UiState
import com.joseph.composeplayground.model.Movie
import com.joseph.composeplayground.util.LoadState


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