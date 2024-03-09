package com.example.data.DataSource.Database.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.Entity.MovieEntity

@Entity
data class DBMovieEntity(
    @PrimaryKey
    val imdbID: String,
    val Title: String,
    val Year: String,
    val Poster: String
)

fun DBMovieEntity.toMovieEntity(): MovieEntity {
    return MovieEntity(
        imdbID = imdbID,
        Title = Title,
        Year = Year,
        Poster = Poster
    )
}

fun MovieEntity.toDBMovieEmtity(): DBMovieEntity {
    return DBMovieEntity(
        imdbID = imdbID,
        Title = Title,
        Year = Year,
        Poster = Poster
    )
}