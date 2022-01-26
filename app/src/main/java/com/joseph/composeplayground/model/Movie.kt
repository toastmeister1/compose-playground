package com.joseph.composeplayground.model

import com.joseph.domain.model.MovieEntity


data class Movie(
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val id: Int?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val voteAverage: Double?,
    val voteCount: Int?
) {

    companion object {
        fun fromEntity(entity: MovieEntity): Movie {
            return Movie (
                adult = entity.adult,
                backdropPath = entity.backdropPath,
                genreIds = entity.genreIds,
                id = entity.id,
                originalLanguage =entity.originalLanguage,
                originalTitle = entity.originalTitle,
                overview = entity.overview,
                popularity = entity.popularity,
                posterPath = entity.posterPath,
                releaseDate = entity.releaseDate,
                title = entity.title,
                voteAverage = entity.voteAverage,
                voteCount = entity.voteCount
            )
        }
    }
}