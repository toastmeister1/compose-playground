package com.joseph.composeplayground.ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.joseph.composeplayground.base.BaseViewModel
import com.joseph.composeplayground.model.Movie
import com.joseph.composeplayground.ui.home.dto.HomeAction
import com.joseph.composeplayground.ui.home.dto.HomeEvent
import com.joseph.composeplayground.ui.home.dto.HomeState
import com.joseph.composeplayground.util.LoadState
import com.joseph.domain.usecases.FetchUpComingMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchUpComingMovieListUseCase: FetchUpComingMovieListUseCase
) : BaseViewModel<HomeState, HomeAction, HomeEvent>(HomeState.getInitial()) {

    init {
        viewModelScope.launch {
            fetchUpComingMovieList(uiState.value)
        }
    }

    override suspend fun handleAction(action: HomeAction) {
        when (action) {
            is HomeAction.FetchUpComingMovieList -> {
                fetchUpComingMovieList(uiState.value)
            }
        }
    }

    private suspend fun fetchUpComingMovieList(state: HomeState) {
        val params = FetchUpComingMovieListUseCase.Params(page = state.upComingMovieList.page)
        fetchUpComingMovieListUseCase.invoke(params = params)
            .collectWithCallback(
                onSuccess = { movieListEntity ->
                    val newState = uiState.value.copy(
                        loadState         = LoadState.Idle,
                        upComingMovieList = uiState.value.upComingMovieList.copy(
                            movies = uiState.value.upComingMovieList.movies + movieListEntity.results.map { Movie.fromEntity(it) },
                            page   = movieListEntity.page!!,
                        )
                    )

                    updateState(newState)
                },

                onFailed = { message ->
                    val newState = uiState.value.copy(loadState = LoadState.Failed)
                    updateState(newState)

                    dispatchEvent(HomeEvent.FetchMovieListFailed(message = message))
                }
            )
    }
}