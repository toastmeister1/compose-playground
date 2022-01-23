package com.joseph.data.remote.service

import com.joseph.data.BuildConfig
import com.joseph.data.model.MovieListModel
import com.joseph.data.model.MovieModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApi {

    companion object {
        const val SPOTIFY_BASE_URL = "https://api.themoviedb.org/3/movie"

        const val LANGUAGE_KR = "ko_KR"
        const val LANGUAGE_EN = "en-US"
    }

    @POST("/movie/upcoming")
    suspend fun fetchUpComingMovieList(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = LANGUAGE_KR
    ): MovieListModel

    @GET("/movie/{movie_id}")
    suspend fun fetchMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = LANGUAGE_KR
    ): MovieModel
}