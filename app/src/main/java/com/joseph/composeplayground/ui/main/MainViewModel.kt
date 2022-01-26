package com.joseph.composeplayground.ui.main

import androidx.lifecycle.viewModelScope
import com.joseph.composeplayground.base.BaseViewModel
import com.joseph.composeplayground.model.Movie
import com.joseph.domain.usecases.FetchUpComingMovieListUseCase
import com.joseph.domain.util.TaskResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val fetchUpComingMovieListUseCase: FetchUpComingMovieListUseCase
) : BaseViewModel<MainState, MainUserAction, MainRenderAction>() {

    override suspend fun handleInteractAction() {
        fetchUpComingMovieListUseCase.invoke(params = FetchUpComingMovieListUseCase.Params(page = 0, language = "ko-KR"))
            .collectWithCallback(
                onSuccess = { movieListEntity ->
                    val fetchedList = movieListEntity.movieModels?.map { entity -> Movie.fromEntity(entity) } ?: emptyList()
                    _state.value = _state.value?.copyUpComingMovieState(upComingMovieList = fetchedList)
                },
                onFailed = { errorMessage ->
                    dispatchRenderAction(MainRenderAction.ShowToast(errorMessage))
                }
            )
    }
}