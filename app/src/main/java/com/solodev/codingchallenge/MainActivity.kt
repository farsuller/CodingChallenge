package com.solodev.codingchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.solodev.codingchallenge.presentation.screens.home.HomeScreen
import com.solodev.codingchallenge.presentation.screens.home.HomeViewModel
import com.solodev.codingchallenge.ui.theme.CodingChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodingChallengeTheme(
                dynamicColor = false,
            ) {
                val viewModel: HomeViewModel = hiltViewModel()
                val movies = viewModel.movies.collectAsLazyPagingItems()

                HomeScreen(movies = movies,
                    navigateToSearch = {

                    },
                    navigateToDetails = {
                    })
            }
        }
    }
}