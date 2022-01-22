package com.joseph.data.model


import com.joseph.domain.model.MovieListEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@Serializable
data class MovieListModel(
    val page: Int?,
    val movieModels: List<MovieModel>?
) {
    fun toEntity(): MovieListEntity {
        return MovieListEntity(
            page = this.page,
            movieModels = this.movieModels?.map { it.toEntity() }
        )
    }
}