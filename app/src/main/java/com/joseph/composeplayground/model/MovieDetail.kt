package com.joseph.composeplayground.model

import com.joseph.domain.model.MovieDetailEntity


data class MovieDetail(
    val adult: Boolean?,
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<Genre>?,
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
    data class Genre(
        val id: Int?,
        val name: String?
    ) {
        companion object {
            fun fromEntity(entity: MovieDetailEntity.GenreEntity): Genre {
                return Genre(
                    id   = entity.id,
                    name = entity.name
                )
            }
        }
    }

    companion object {
        fun fromEntity(entity: MovieDetailEntity): MovieDetail {
            return MovieDetail(
                adult            = entity.adult,
                backdropPath     = entity.backdropPath,
                budget           = entity.budget,
                genres           = entity.genres?.map { Genre.fromEntity(it) },
                homepage         = entity.homepage,
                id               = entity.id,
                imdbId           = entity.imdbId,
                originalLanguage = entity.originalLanguage,
                overview         = entity.overview,
                originalTitle    = entity.originalTitle,
                popularity       = entity.popularity,
                posterPath       = entity.posterPath,
                releaseDate      = entity.releaseDate,
                revenue          = entity.revenue,
                runtime          = entity.runtime,
                status           = entity.status,
                tagline          = entity.tagline,
                title            = entity.title,
                video            = entity.video,
                voteAverage      = entity.voteAverage,
                voteCount        = entity.voteCount
            )
        }
    }

}