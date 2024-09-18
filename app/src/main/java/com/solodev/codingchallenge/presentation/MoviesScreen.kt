package com.solodev.codingchallenge.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.presentation.common.MoviesList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(movies: List<Movie>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies") },

            )
        },
        content = { paddingValues ->
            MoviesList(
                modifier = Modifier.padding(paddingValues),
                movies = movies,
                onClick = { movie ->
                    // Handle click event, e.g., navigate to detail screen
                    println("Selected movie: ${movie.trackName}")
                }
            )
        }
    )
}