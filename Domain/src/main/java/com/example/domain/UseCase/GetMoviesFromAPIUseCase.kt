package com.example.domain.UseCase

import com.example.domain.Entity.MovieEntity
import com.example.domain.Repository.APIMovieRepository
import javax.inject.Inject

class GetMoviesFromAPIUseCase @Inject constructor(private val movieRepository: APIMovieRepository) {
    fun invoke(title: String, year: String?): List<MovieEntity>? {
        return movieRepository.getMovies(title, year)
    }
}