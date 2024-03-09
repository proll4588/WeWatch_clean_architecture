package com.example.domain.UseCase

import com.example.domain.Entity.MovieEntity
import com.example.domain.Repository.DBMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesFromDBUseCase @Inject constructor(private val dbMoviesRepository: DBMovieRepository) {
    fun invoke(): Flow<List<MovieEntity>> {
        return dbMoviesRepository.allMovies
    }
}