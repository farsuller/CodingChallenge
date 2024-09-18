package com.solodev.codingchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.solodev.codingchallenge.presentation.MovieViewModel
import com.solodev.codingchallenge.presentation.MoviesScreen
import com.solodev.codingchallenge.presentation.common.EmptyScreen
import com.solodev.codingchallenge.ui.theme.CodingChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodingChallengeTheme {
                val viewModel: MovieViewModel = hiltViewModel()
                val movies by viewModel.movies.observeAsState(emptyList())

                movies.let { m ->
                    if (m != null) {
                        MoviesScreen(movies = m)
                    } else EmptyScreen()
                }
            }
        }
    }
}