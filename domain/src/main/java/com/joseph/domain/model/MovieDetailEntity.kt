package com.joseph.domain.model


data class MovieDetailEntity(
    val adult: Boolean?,
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<GenreEntity>?,
    val homepage: String?,
    val id: Int?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
) {
    data class GenreEntity(
        val id: Int?,
        val name: String?
    )
}