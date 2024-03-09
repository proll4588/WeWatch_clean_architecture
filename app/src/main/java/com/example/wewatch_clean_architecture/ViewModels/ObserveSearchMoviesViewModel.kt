package com.example.wewatch_clean_architecture.ViewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Entity.MovieEntity
import com.example.domain.UseCase.GetMoviesFromAPIUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObserveSearchMoviesViewModel @Inject constructor(
    private val detMoviesFromAPIUseCase: GetMoviesFromAPIUseCase
) : ViewModel() {
    var searchedMovies: MutableState<List<MovieEntity>?> = mutableStateOf(listOf())
        private set

    fun searchMovies(title: String, year: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val found = detMoviesFromAPIUseCase.invoke(title, year)
            searchedMovies.value = found
        }
    }
}