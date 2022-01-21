package com.joseph.data.di

import com.joseph.data.remote.service.MovieApi
import com.joseph.data.repo.MovieRepositoryImpl
import com.joseph.domain.interact.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideMovieRepository(movieService: MovieApi): MovieRepository {
        return MovieRepositoryImpl(movieService = movieService)
    }

}