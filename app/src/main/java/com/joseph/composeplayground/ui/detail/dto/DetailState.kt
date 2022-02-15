package com.joseph.composeplayground.ui.detail.dto

import com.joseph.composeplayground.base.mvi.dto.UiState
import com.joseph.composeplayground.model.Movie
import com.joseph.composeplayground.model.MovieDetail
import com.joseph.composeplayground.ui.common.dto.MovieListState
import com.joseph.composeplayground.util.LoadState


data class DetailState(
    val loadState: LoadState = LoadState.Loading,
    val movieId: Int = -1,
    val movieDetail: MovieDetail? = null,
    val similarMovies: MovieListState = MovieListState.getInitial()
) : UiState