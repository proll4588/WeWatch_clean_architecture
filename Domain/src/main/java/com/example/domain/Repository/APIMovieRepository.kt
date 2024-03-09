package com.example.domain.Repository

import com.example.domain.Entity.MovieEntity

interface APIMovieRepository {
    fun getMovies(title: String, year: String?): List<MovieEntity>?
}