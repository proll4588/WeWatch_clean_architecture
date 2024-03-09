package com.example.domain.Repository

import com.example.domain.Entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface DBMovieRepository {
    val allMovies: Flow<List<MovieEntity>>
    suspend fun insert(movie: MovieEntity)
    suspend fun delete(movie: MovieEntity)
}