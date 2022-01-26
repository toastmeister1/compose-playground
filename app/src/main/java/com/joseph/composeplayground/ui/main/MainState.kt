package com.joseph.composeplayground.ui.main

import com.joseph.composeplayground.base.component.State
import com.joseph.composeplayground.model.Movie


internal data class MainState(
    val upComingMovieList: List<Movie>
) : State {

    fun copyUpComingMovieState(upComingMovieList: List<Movie>): MainState {
        return this.copy(
            upComingMovieList = upComingMovieList
        )
    }

    companion object {
        fun getInitial(): MainState {
            return MainState(
                upComingMovieList = emptyList()
            )
        }
    }
}