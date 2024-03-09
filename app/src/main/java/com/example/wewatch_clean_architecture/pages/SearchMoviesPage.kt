package com.example.wewatch_clean_architecture.pages


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.wewatch_clean_architecture.ViewModels.SearchMoviesViewModel
import com.example.wewatch_clean_architecture.ViewModels.SelectingMovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMoviesPage(
    onNavigateBack: () -> Unit,
    onNavigateToObserveSearchMoviesPage: (title: String, year: String) -> Unit,
    selectingViewModel: SelectingMovieViewModel = hiltViewModel(),
    viewModel: SearchMoviesViewModel = hiltViewModel()
) {
    val selectedMovie by selectingViewModel.selectedMovie.collectAsState()

    val (title, setTitle) = rememberSaveable {
        mutableStateOf("")
    }

    val (year, setYear) = rememberSaveable {
        mutableStateOf("")
    }

    fun handleSearch() {
        onNavigateToObserveSearchMoviesPage(title, year)
    }

    fun handleAdd() {
        if (selectedMovie !== null) {
            viewModel.addMovie(selectedMovie!!)
            selectingViewModel.clear()
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text("Add Movie")
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
        Column(
            modifier = Modifier.padding(padding),
        ) {
            OutlinedTextField(
                value = selectedMovie?.Title ?: title,
                onValueChange = setTitle,
                label = { Text(text = "Movie Title") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                readOnly = selectedMovie !== null
            )

            OutlinedTextField(
                value = selectedMovie?.Year ?: year,
                onValueChange = {
                    if (it.length < 5)
                        setYear(it)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text(text = "Movie Year") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = selectedMovie !== null
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { handleAdd() },
                    enabled = selectedMovie !== null
                ) {
                    Text(text = "Add")
                }

                Button(onClick = { selectingViewModel.clear() }, enabled = selectedMovie !== null) {
                    Text(text = "Clear")
                }

                Button(onClick = { handleSearch() }, enabled = title.isNotEmpty()) {
                    Text(text = "Search")
                }
            }

            if (selectedMovie != null) {
                AsyncImage(
                    model = selectedMovie!!.Poster,
                    contentDescription = "Movie ${selectedMovie!!.Title}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(vertical = 16.dp),
                    alignment = Alignment.Center
                )
            }
        }
    }

}
