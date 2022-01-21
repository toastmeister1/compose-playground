package com.joseph.data.repo

import com.joseph.data.remote.service.MovieApi
import com.joseph.domain.interact.MovieRepository
import com.joseph.domain.model.MovieListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject


internal class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieApi
) : MovieRepository {
    override fun fetchUpComingMovieList(page: Int, language: String): Flow<MovieListEntity> {
        runCatching {
            movieService.fetchUpComingMovieList(page = page, language = language)
        }.fold(
            onSuccess = { models ->
                return flowOf( models.toEntity() )
            },
            onFailure = {
                throw RuntimeException("에러는 허용하지 않는다.")
            }
        )
    }
}