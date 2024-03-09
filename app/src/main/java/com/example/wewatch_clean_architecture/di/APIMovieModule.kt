package com.example.wewatch_clean_architecture.di

import com.example.data.DataSource.API.Client.ApiMovieClient
import com.example.data.DataSource.API.Service.MovieAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object APIMovieModule {
    @Provides
    fun provideAPIMovieService(httpClient: OkHttpClient): MovieAPIService {
        return ApiMovieClient(httpClient).getApiService()
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }
}