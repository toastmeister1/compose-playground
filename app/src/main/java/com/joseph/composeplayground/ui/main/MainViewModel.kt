package com.joseph.composeplayground.ui.main

import androidx.lifecycle.viewModelScope
import com.joseph.composeplayground.base.BaseViewModel
import com.joseph.composeplayground.model.Movie
import com.joseph.domain.usecases.FetchUpComingMovieListUseCase
import com.joseph.domain.util.TaskResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val fetchUpComingMovieListUseCase: FetchUpComingMovieListUseCase,
) : BaseViewModel<MainState, MainAction, MainEvent>() {

    private val reducer = MainReducer(MainState.getInitial())

    override val state: StateFlow<MainState>
        get() = reducer.state

    override suspend fun handleUiEvent(event: MainEvent) {
        when(event) {
            is MainEvent.RequestUpComingMovieList -> {
                fetchUpComingMovieListUseCase.invoke(FetchUpComingMovieListUseCase.Params(page = 1, "ko-KR"))
                    .collectWithCallback(
                        onSuccess = { movieListEntity ->
                            reducer.sendEvent()
                        },
                        onFailed = { errorMessage ->

                        }
                    )
            }
        }
    }


}