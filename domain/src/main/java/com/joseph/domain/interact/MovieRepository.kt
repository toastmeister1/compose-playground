package com.joseph.domain.interact

import com.joseph.domain.model.MovieListEntity
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    fun fetchUpComingMovieList(page: Int, language: String): Flow<MovieListEntity>
}