package com.example.domain.UseCase

import com.example.domain.Entity.MovieEntity
import com.example.domain.Repository.DBMovieRepository
import javax.inject.Inject

class AddMovieToDBUseCase @Inject constructor(private val dbMoviesRepository: DBMovieRepository) {
    suspend fun invoke(movie: MovieEntity) {
        dbMoviesRepository.insert(movie)
    }
}