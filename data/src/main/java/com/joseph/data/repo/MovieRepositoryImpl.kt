package com.joseph.data.repo

import com.joseph.data.remote.service.MovieApi
import com.joseph.domain.interact.MovieRepository
import com.joseph.domain.model.MovieListEntity
import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


internal class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieApi
) : MovieRepository {
    override suspend fun fetchUpComingMovieList(page: Int, language: String): Flow<TaskResult<MovieListEntity>> {
        runCatching {
            movieService.fetchUpComingMovieList(page = page, language = language)
        }.fold(
            onSuccess = { model ->
                return flow {
                    TaskResult.Success(model.toEntity())
                }
            },
            onFailure = {
                return flow {
                    TaskResult.Failed("[TaskFailed] : ${it.message}")
                }
            }
        )
    }
}