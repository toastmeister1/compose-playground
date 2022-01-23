package com.joseph.data.model


import com.joseph.domain.model.MovieEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
    val adult: Boolean?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("genre_ids")
    val genreIds: List<Int>?,
    val id: Int?,
    @SerialName("original_langua")
    val originalLanguage: String?,
    @SerialName("original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    val title: String?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
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