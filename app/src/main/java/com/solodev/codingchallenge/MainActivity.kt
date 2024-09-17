package com.solodev.codingchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.solodev.codingchallenge.presentation.MovieViewModel
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

                viewModel.movies.observe(this) {
                    println("Movies: $it")
                }
            }
        }
    }
}