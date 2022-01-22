package com.joseph.domain.usecases

import com.joseph.domain.interact.MovieRepository
import com.joseph.domain.model.MovieListEntity
import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FetchPopularMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): BaseUseCase<Flow<TaskResult<MovieListEntity>>, FetchPopularMovieListUseCase.Params>() {

    class Params(
        val page: Int,
        val language: String
    )

    override suspend fun invoke(params: Params): Flow<TaskResult<MovieListEntity>> {
        return movieRepository.fetchUpComingMovieList(params.page, params.language)
    }

}