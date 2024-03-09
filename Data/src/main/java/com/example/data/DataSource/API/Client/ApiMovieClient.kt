package com.example.data.DataSource.API.Client

import com.example.data.DataSource.API.Service.MovieAPIService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiMovieClient @Inject constructor(private val httpClient: OkHttpClient) {
    private lateinit var apiService: MovieAPIService

    fun getApiService(): MovieAPIService {
        if (!::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(MovieAPIService.BASE_URL)
                .client(httpClient)
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).build()

            apiService = retrofit.create(MovieAPIService::class.java)
        }

        return apiService
    }
}