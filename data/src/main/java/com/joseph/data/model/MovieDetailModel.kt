package com.joseph.data.model


import com.joseph.domain.model.MovieDetailEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailModel(
    @SerialName("adult")
    val adult: Boolean?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("budget")
    val budget: Int?,
    @SerialName("genres")
    val genres: List<GenreModel>?,
    @SerialName("homepage")
    val homepage: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("imdb_id")
    val imdbId: String?,
    @SerialName("original_language")
    val originalLanguage: String?,
    @SerialName("original_title")
    val originalTitle: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: Double?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("revenue")
    val revenue: Int?,
    @SerialName("runtime")
    val runtime: Int?,
    @SerialName("status")
    val status: String?,
    @SerialName("tagline")
    val tagline: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("video")
    val video: Boolean?,
    @SerialName("vote_average")
    val voteAverage: Double?,
    @SerialName("vote_count")
    val voteCount: Int?
) {
    @Serializable
    data class GenreModel(
        @SerialName("id")
        val id: Int?,
        @SerialName("name")
        val name: String?
    ) {
        fun toEntity(): MovieDetailEntity.GenreEntity {
            return MovieDetailEntity.GenreEntity(id = this.id, name = this.name)
        }
    }

    fun toEntity(): MovieDetailEntity {
        return MovieDetailEntity(
            adult = this.adult,
            backdropPath = this.backdropPath,
            budget = this.budget,
            genres = this.genres?.map { it.toEntity() },
            homepage = this.homepage,
            id = this.id,
            imdbId = this.imdbId,
            originalLanguage = this.originalLanguage,
            overview = this.overview,
            originalTitle = this.originalTitle,
            popularity = this.popularity,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            revenue = this.revenue,
            runtime = this.runtime,
            status = this.status,
            tagline = this.tagline,
            title = this.title,
            video = this.video,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount
        )
    }
}