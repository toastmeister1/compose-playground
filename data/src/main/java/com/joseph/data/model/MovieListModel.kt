package com.joseph.data.model


import com.joseph.domain.model.MovieListEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListModel(
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val movieModels: List<MovieModel>?,
) {
    fun toEntity(): MovieListEntity {
        return MovieListEntity(
            page = this.page,
            movieModels = this.movieModels?.map { it.toEntity() }
        )
    }
}