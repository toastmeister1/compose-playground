package com.joseph.domain.usecases

import com.joseph.domain.interact.MovieRepository
import com.joseph.domain.model.MovieListEntity
import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FetchUpComingMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): BaseUseCase<Flow<TaskResult<MovieListEntity>>, FetchUpComingMovieListUseCase.Params>() {

    class Params(
        val page: Int
    )

    override suspend fun invoke(params: Params): Flow<TaskResult<MovieListEntity>> {
        return movieRepository.fetchUpComingMovieList(params.page)
            .toResult()
    }

}