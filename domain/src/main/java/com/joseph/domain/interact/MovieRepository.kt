package com.joseph.domain.interact

import com.joseph.domain.model.MovieListEntity


interface MovieRepository {
    fun fetchUpComingMovieList(page: Int, language: String): MovieListEntity
}