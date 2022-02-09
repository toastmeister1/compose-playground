package com.joseph.composeplayground.ui.home

import androidx.lifecycle.viewModelScope
import com.joseph.composeplayground.base.BaseViewModel
import com.joseph.composeplayground.model.Movie
import com.joseph.composeplayground.ui.home.dto.HomeAction
import com.joseph.composeplayground.ui.home.dto.HomeEvent
import com.joseph.composeplayground.ui.home.dto.HomeState
import com.joseph.composeplayground.util.LoadState
import com.joseph.domain.usecases.FetchPopularMovieListUseCase
import com.joseph.domain.usecases.FetchUpComingMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchUpComingMovieListUseCase: FetchUpComingMovieListUseCase,
    private val fetchPopularMovieListUseCase: FetchPopularMovieListUseCase
) : BaseViewModel<HomeState, HomeAction, HomeEvent>(HomeState.getInitial()) {

    init {
        viewModelScope.launch {
            fetchUpComingMovieList(uiState.value)
            fetchPopularMovieList(uiState.value)
        }
    }

    override suspend fun handleAction(action: HomeAction) {
        when (action) {
            is HomeAction.FetchUpComingMovieList -> {
                fetchUpComingMovieList(uiState.value)
            }
            is HomeAction.FetchPopularMovieList -> {
                fetchPopularMovieList(uiState.value)
            }
        }
    }

    private suspend fun fetchUpComingMovieList(state: HomeState) {
        updateState(newState = state.copy(
            upComingMoviesState = state.upComingMoviesState.copy(
                loadState = LoadState.Loading,
                endReached = true
            )
        ))

        val params = FetchUpComingMovieListUseCase.Params(page = state.upComingMoviesState.page)
        fetchUpComingMovieListUseCase.invoke(params = params)
            .collectWithCallback(
                onSuccess = { movieListEntity ->
                    val newState = uiState.value.copy(
                        upComingMoviesState = uiState.value.upComingMoviesState.copy(
                            loadState = LoadState.Idle,
                            movies = uiState.value.upComingMoviesState.movies + movieListEntity.results.map { Movie.fromEntity(it) },
                            page   = movieListEntity.page + 1,
                            endReached = false
                        )
                    )

                    updateState(newState)
                },

                onFailed = { message ->
                    updateState(newState = uiState.value.copy(
                        upComingMoviesState = state.upComingMoviesState.copy(
                            loadState = LoadState.Failed
                        )
                    ))
                }
            )
    }

    private suspend fun fetchPopularMovieList(state: HomeState) {
        updateState(newState = state.copy(
            popularMoviesState = state.popularMoviesState.copy(
                loadState = LoadState.Loading,
                endReached = true
            )
        ))

        val params = FetchPopularMovieListUseCase.Params(page = state.popularMoviesState.page)
        fetchPopularMovieListUseCase.invoke(params = params)
            .collectWithCallback(
                onSuccess = { movieListEntity ->
                    val newState = uiState.value.copy(
                        popularMoviesState = uiState.value.popularMoviesState.copy(
                            loadState = LoadState.Idle,
                            movies = uiState.value.popularMoviesState.movies + movieListEntity.results.map { Movie.fromEntity(it) },
                            page   = movieListEntity.page + 1,
                            endReached = false
                        )
                    )

                    updateState(newState)
                },

                onFailed = { message ->
                    updateState(newState = uiState.value.copy(
                        upComingMoviesState = state.popularMoviesState.copy(
                            loadState = LoadState.Failed
                        )
                    ))
                }
            )
    }
}