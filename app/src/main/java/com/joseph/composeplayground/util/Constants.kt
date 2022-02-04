package com.joseph.composeplayground.util

/*
* [BASE_IMAGE_URL_500 or ORIGINAL] + [backdropPath or posterPath] 로 조합해야지 이미지 URL이 완성됩니다.
* 각각은 사이즈를 나타내며 ORIGINAL은 원본화질(높은거)입니다.
 */

object Constants{
    const val BASE_IMAGE_URL_500 = "https://image.tmdb.org/t/p/w500"
    const val BASE_IMAGE_URL_ORIGINAL = "https://image.tmdb.org/t/p/original"
}