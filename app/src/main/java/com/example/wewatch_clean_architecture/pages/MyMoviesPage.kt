package com.example.wewatch_clean_architecture.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.Entity.MovieEntity
import com.example.wewatch_clean_architecture.ViewModels.MyMoviesViewModel
import com.example.wewatch_clean_architecture.components.IconWithText
import com.example.wewatch_clean_architecture.components.MovieCard


@SuppressLint("FlowOperatorInvokedInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyMoviesPage(
    onNavigationToSearchMoviesPage: () -> Unit,
    viewModel: MyMoviesViewModel = hiltViewModel()
) {
    val movies = viewModel.movies.collectAsState(initial = emptyList())
    val selectedMovies = remember {
        mutableStateListOf<MovieEntity>()
    }

    fun isSelected(movie: MovieEntity): Boolean {
        val index = selectedMovies.indexOf(movie)
        return index != -1
    }

    fun toggleSelect(movie: MovieEntity) {
        val index = selectedMovies.indexOf(movie)
        if (index == -1) {
            selectedMovies.add(movie)
        } else {
            selectedMovies.removeAt(index)
        }
    }

    fun handleDelete() {
        selectedMovies.forEach { viewModel.removeMovie(it) }
        selectedMovies.clear()
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigationToSearchMoviesPage() }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = { handleDelete() }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text("Movies to Watch")
                },
            )
        },
        contentWindowInsets = WindowInsets(left = 8.dp, right = 8.dp)
    ) { padding ->
        if (movies.value.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                IconWithText(
                    imageVector = Icons.Filled.Movie,
                    text = "There are currently no movies in your watch list. Tap the button below to get started!"
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(padding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(movies.value.size) { index ->
                    val movie = movies.value[index]

                    MovieCard(
                        movie = movie,
                        onClick = { toggleSelect(movie) },
                    ) {
                        Checkbox(
                            checked = isSelected(movie),
                            onCheckedChange = { toggleSelect(movie) })
                    }
                }
            }
        }
    }
}
