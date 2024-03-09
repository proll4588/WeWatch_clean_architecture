package com.example.data.DataSource.API.Repository

import com.example.data.DataSource.API.Entity.toMovieEntity
import com.example.data.DataSource.API.Service.MovieAPIService
import com.example.domain.Entity.MovieEntity
import com.example.domain.Repository.APIMovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIMovieRepositoryImpl @Inject constructor(private val api: MovieAPIService) :
    APIMovieRepository {

    override fun getMovies(
        title: String,
        year: String?
    ): List<MovieEntity>? {
        return api
            .getMovieList(title = title, year = year)
            .execute()
            .body()
            ?.Search
            ?.toList()
            ?.map { it.toMovieEntity() }
    }
}