package com.example.wewatch_clean_architecture.di

import com.example.data.DataSource.API.Repository.APIMovieRepositoryImpl
import com.example.data.DataSource.Database.DBMovieRepositoryImpl
import com.example.domain.Repository.APIMovieRepository
import com.example.domain.Repository.DBMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun provideApiRepositury(repository: APIMovieRepositoryImpl): APIMovieRepository

    @Binds
    fun provideDBRepository(repository: DBMovieRepositoryImpl): DBMovieRepository
}