package com.joseph.domain.interact

import com.joseph.domain.model.MovieDetailEntity
import com.joseph.domain.model.MovieListEntity
import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    suspend fun fetchUpComingMovieList(page: Int): Flow<MovieListEntity>
    suspend fun fetchPopularMovieList(page: Int): Flow<MovieListEntity>
    suspend fun fetchSimilarMovieList(movieId: Int, page: Int): Flow<MovieListEntity>
    suspend fun fetchMovieDetail(movieId: Int): Flow<MovieDetailEntity>
}