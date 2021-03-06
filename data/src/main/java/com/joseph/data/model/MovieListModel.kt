package com.joseph.data.model


import com.joseph.domain.model.MovieListEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListModel(
    val page: Int?,
    @SerialName("results")
    val results: List<MovieModel>?
) {
    fun toEntity(): MovieListEntity {
        return MovieListEntity(
            page = this.page!!,
            results = this.results!!.map { it.toEntity() }
        )
    }
}