package com.joseph.data.model


import com.joseph.domain.model.MovieEntity
import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
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
    fun toEntity(): MovieEntity {
        return MovieEntity(
            adult = this.adult,
            backdropPath = this.backdropPath,
            genreIds = this.genreIds,
            id = this.id,
            originalLanguage =this.originalLanguage,
            originalTitle = this.originalTitle,
            overview = this.overview,
            popularity = this.popularity,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            title = this.title,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount
        )
    }
}