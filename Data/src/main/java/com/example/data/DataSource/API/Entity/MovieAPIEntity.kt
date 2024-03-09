package com.example.data.DataSource.API.Entity

import com.example.domain.Entity.MovieEntity

data class MovieAPIEntity(
    val Title: String,
    val Year: String,
    val Poster: String,
    val imdbID: String
)

fun MovieAPIEntity.toMovieEntity(): MovieEntity {
    return MovieEntity(
        imdbID = imdbID,
        Title = Title,
        Year = Year,
        Poster = Poster
    )
}