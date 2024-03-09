package com.example.wewatch_clean_architecture.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.Entity.MovieEntity


@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movie: MovieEntity,
    onClick: () -> Unit,
    content: @Composable (() -> Unit) = {},
) {
    MovieCard(
        onClick = onClick,
        year = movie.Year,
        title = movie.Title,
        imgUrl = movie.Poster,
        content = content,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieCard(
    modifier: Modifier = Modifier,
    imgUrl: String,
    title: String,
    year: String,
    content: @Composable (() -> Unit) = {},
    onClick: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(all = 0.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Row {
            AsyncImage(
                model = imgUrl,
                contentDescription = "Movie $title",
                modifier = Modifier
                    .width(80.dp)
                    .fillMaxHeight(),
                alignment = Alignment.TopStart
            )
            Column(modifier = Modifier.width(250.dp)) {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)
                Text(text = year)
            }


            Column(
                modifier = Modifier
                    .width(20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
}