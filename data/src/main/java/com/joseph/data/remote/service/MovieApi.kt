package com.joseph.data.remote.service

import com.joseph.data.BuildConfig
import com.joseph.data.model.MovieDetailModel
import com.joseph.data.model.MovieListModel
import com.joseph.data.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"

        const val LANGUAGE_KR = "ko-KR"
        const val LANGUAGE_EN = "en-US"
    }

    @GET("movie/upcoming")
    suspend fun fetchUpComingMovieList(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = LANGUAGE_KR
    ): MovieListModel

    @GET("movie/popular")
    suspend fun fetchPopularMovieList(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = LANGUAGE_KR
    ): MovieListModel

    @GET("movie/{movie_id}/similar")
    suspend fun fetchSimilarMovieList(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = LANGUAGE_KR
    ): MovieListModel

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = LANGUAGE_KR
    ): MovieDetailModel

}