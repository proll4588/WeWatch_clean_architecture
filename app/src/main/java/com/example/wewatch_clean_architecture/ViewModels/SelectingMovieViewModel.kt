package com.example.wewatch_clean_architecture.ViewModels

import androidx.lifecycle.ViewModel
import com.example.domain.Entity.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SelectingMovieViewModel @Inject constructor() : ViewModel() {
    private val _selectedMovie = MutableStateFlow<MovieEntity?>(null)

    val selectedMovie: StateFlow<MovieEntity?> = _selectedMovie.asStateFlow()

    fun selectMovie(movie: MovieEntity) {
        _selectedMovie.value = movie
    }

    fun clear() {
        _selectedMovie.value = null
    }
}