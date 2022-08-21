package com.joseph.composeplayground.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.joseph.composeplayground.base.BaseViewModel
import com.joseph.composeplayground.model.Movie
import com.joseph.composeplayground.model.MovieDetail
import com.joseph.composeplayground.ui.common.dto.MovieListState
import com.joseph.composeplayground.ui.detail.dto.DetailAction
import com.joseph.composeplayground.ui.detail.dto.DetailEvent
import com.joseph.composeplayground.ui.detail.dto.DetailState
import com.joseph.composeplayground.util.LoadState
import com.joseph.domain.usecases.FetchMovieDetailUseCase
import com.joseph.domain.usecases.FetchSimilarMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val fetchInitialMovieDetailUseCase: FetchMovieDetailUseCase,
    private val fetchSimilarMovieListUseCase: FetchSimilarMovieListUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailState, DetailAction, DetailEvent>(DetailState()) {

    init {
        val movieId = savedStateHandle.get<Int>("movieId")!!
        viewModelScope.launch {
            fetchInitialDetailInformation(movieId)
        }
    }

    override suspend fun handleAction(action: DetailAction) {
    }

    private suspend fun fetchInitialDetailInformation(movieId: Int) {
        fetchInitialMovieDetailUseCase(params = FetchMovieDetailUseCase.Params(movieId = movieId))
            .collectWithCallback(
                onSuccess = { initialMovieDetail ->
                    val detailInformation = initialMovieDetail.first
                    val similarMovieList = initialMovieDetail.second

                    updateState(uiState.value.copy(
                        loadState = LoadState.Idle,
                        movieId = movieId,
                        movieDetail = MovieDetail.fromEntity(detailInformation),
                        similarMovies = uiState.value.similarMovies.copy(
                            loadState = LoadState.Idle,
                            movies = similarMovieList.results.map { Movie.fromEntity(it) },
                            page = uiState.value.similarMovies.page + 1,
                            endReached = false
                        )
                    ))
                },

                onFailed = {
                    //TODO retry 구현해보기
                }
            )
    }

    private suspend fun fetchSimilarMovieList(movieId: Int, page: Int) {
        val params = FetchSimilarMovieListUseCase.Params(
            movieId = movieId,
            page = page
        )
        fetchSimilarMovieListUseCase(params = params)
            .collectWithCallback(
                onSuccess = {
                    //TODO
                },
                onFailed = {
                    //TODO
                }
            )
    }
}