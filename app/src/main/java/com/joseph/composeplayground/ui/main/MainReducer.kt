package com.joseph.composeplayground.ui.main

import com.joseph.composeplayground.base.component.Reducer


internal class MainReducer(initialState: MainState) : Reducer<MainState, MainEvent>(initialState) {

    override fun reduce(oldState: MainState, event: MainEvent) {
        when(event) {
            is MainEvent.RequestUpComingMovieList -> {
                val newState = state.value.copy(upComingMovieList = state.value.upComingMovieList + event.movies)
                setState(newState)
            }
        }
    }

}