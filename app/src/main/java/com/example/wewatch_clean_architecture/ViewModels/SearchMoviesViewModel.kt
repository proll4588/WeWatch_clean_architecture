package com.example.wewatch_clean_architecture.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Entity.MovieEntity
import com.example.domain.UseCase.AddMovieToDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val addMovieToDBUseCase: AddMovieToDBUseCase
) : ViewModel() {
    fun addMovie(movie: MovieEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addMovieToDBUseCase.invoke(movie)
        }
    }
}