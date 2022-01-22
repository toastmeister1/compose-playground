package com.joseph.composeplayground.di

import com.joseph.domain.interact.MovieRepository
import com.joseph.domain.usecases.FetchPopularMovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideFetchPopularMovieListUseCase(movieRepository: MovieRepository): FetchPopularMovieListUseCase {
        return FetchPopularMovieListUseCase(movieRepository)
    }

}