package com.example.wewatch_clean_architecture.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Entity.MovieEntity
import com.example.domain.UseCase.DeleteMovieFromDBUseCase
import com.example.domain.UseCase.GetMoviesFromDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyMoviesViewModel @Inject constructor(
    private val getMoviesFromDBUseCase: GetMoviesFromDBUseCase,
    private val deleteMovieFromDBUseCase: DeleteMovieFromDBUseCase
) : ViewModel() {
    val movies = getMoviesFromDBUseCase.invoke()

    fun removeMovie(movie: MovieEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteMovieFromDBUseCase.invoke(movie)
        }
    }
}