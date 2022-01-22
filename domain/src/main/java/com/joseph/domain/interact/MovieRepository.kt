package com.joseph.domain.interact

import com.joseph.domain.model.MovieListEntity
import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    suspend fun fetchUpComingMovieList(page: Int, language: String): Flow<TaskResult<MovieListEntity>>
}