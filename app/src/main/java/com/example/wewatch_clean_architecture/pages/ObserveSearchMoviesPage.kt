package com.example.wewatch_clean_architecture.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wewatch_clean_architecture.ViewModels.ObserveSearchMoviesViewModel
import com.example.wewatch_clean_architecture.ViewModels.SelectingMovieViewModel
import com.example.wewatch_clean_architecture.components.MovieCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObserveSearchMoviesPage(
    onNavigateBack: () -> Unit,
    title: String,
    year: String? = null,
    viewModal: ObserveSearchMoviesViewModel = hiltViewModel(),
    selectingViewModel: SelectingMovieViewModel = hiltViewModel()
) {
    val movies = remember { viewModal.searchedMovies }

    LaunchedEffect(true) {
        viewModal.searchMovies(title, year)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text("Search Result")
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        contentWindowInsets = WindowInsets(left = 8.dp, right = 8.dp)
    ) { padding ->
        if (movies.value != null) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(padding)
            ) {
                items(movies.value!!.size) {
                    val movie = movies.value!![it]

                    MovieCard(
                        movie = movie,
                        onClick = {
                            selectingViewModel.selectMovie(movie)
                            onNavigateBack()
                        },
                    )
                }
            }
        }
    }
}
