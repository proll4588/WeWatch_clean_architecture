package com.example.data.DataSource.Database

import com.example.data.DataSource.Database.Dao.MovieDao
import com.example.data.DataSource.Database.Entity.toDBMovieEmtity
import com.example.data.DataSource.Database.Entity.toMovieEntity
import com.example.domain.Entity.MovieEntity
import com.example.domain.Repository.DBMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DBMovieRepositoryImpl @Inject constructor(private val movieDao: MovieDao) :
    DBMovieRepository {

    override val allMovies: Flow<List<MovieEntity>> =
        movieDao.getAllMovies().map { movie -> movie.map { it.toMovieEntity() } }


    override suspend fun insert(movie: MovieEntity) {
        movieDao.insert(movie.toDBMovieEmtity())
    }

    override suspend fun delete(movie: MovieEntity) {
        movieDao.delete(movie.toDBMovieEmtity())
    }
}