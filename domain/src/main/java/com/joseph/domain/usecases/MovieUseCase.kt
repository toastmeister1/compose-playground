package com.joseph.domain.usecases

import com.joseph.domain.interact.MovieRepository
import com.joseph.domain.model.MovieDetailEntity
import com.joseph.domain.model.MovieListEntity
import com.joseph.domain.util.TaskResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.zip
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

class FetchPopularMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): BaseUseCase<Flow<TaskResult<MovieListEntity>>, FetchPopularMovieListUseCase.Params>() {

    class Params(
        val page: Int
    )

    override suspend fun invoke(params: Params): Flow<TaskResult<MovieListEntity>> {
        return movieRepository.fetchPopularMovieList(params.page)
            .toResult()
    }

}

class FetchSimilarMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): BaseUseCase<Flow<TaskResult<MovieListEntity>>, FetchSimilarMovieListUseCase.Params>() {

    class Params(
        val movieId: Int,
        val page: Int
    )

    override suspend fun invoke(params: Params): Flow<TaskResult<MovieListEntity>> {
        return movieRepository.fetchSimilarMovieList(params.movieId, params.page)
            .toResult()
    }

}

typealias InitialDetailScreenState = Pair<MovieDetailEntity, MovieListEntity>
class FetchMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
): BaseUseCase<Flow<TaskResult<InitialDetailScreenState>>, FetchMovieDetailUseCase.Params>() {

    class Params(
        val movieId: Int
    )

    override suspend fun invoke(params: FetchMovieDetailUseCase.Params): Flow<TaskResult<InitialDetailScreenState>> {
        return movieRepository.fetchMovieDetail(params.movieId)
            .zip(movieRepository.fetchSimilarMovieList(movieId = params.movieId, page = 1)) { movieDetail, similarMovies ->
                movieDetail to similarMovies
            }
            .toResult()
    }

}

