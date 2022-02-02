package com.joseph.domain.model


data class MovieListEntity(
    val page: Int,
    val results: List<MovieEntity>
)