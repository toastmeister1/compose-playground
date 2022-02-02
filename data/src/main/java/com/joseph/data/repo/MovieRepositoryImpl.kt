package com.joseph.data.repo

import com.joseph.data.remote.service.MovieApi
import com.joseph.domain.interact.MovieRepository
import com.joseph.domain.model.MovieListEntity
import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


internal class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieApi
) : MovieRepository {

    override suspend fun fetchUpComingMovieList(page: Int): Flow<MovieListEntity> {
        val model = movieService.fetchUpComingMovieList(page = page)
        return flow {
            emit(TaskResult.Success(model))
        }.map {
            it.data.toEntity()
        }
    }
}