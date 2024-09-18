package com.solodev.codingchallenge.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.ui.theme.CodingChallengeTheme

@Composable
fun MoviesList(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onClick: (Movie) -> Unit,
) {
    if (movies.isEmpty()) {
        EmptyScreen()
    }
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(all = 6.dp),
    ) {
        items(count = movies.size) { m ->
            val movie = movies[m]
            MovieCard(movie = movie, onClick = { onClick(movie) })
        }
    }
}

@Composable
fun MoviesList(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<Movie>,
    onClick: (Movie) -> Unit,
) {
    val handlePagingResult = handlePagingResult(movies = movies)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(all = 6.dp),
        ) {
            items(count = movies.itemCount) { movie ->
                movies[movie]?.let { a ->
                    MovieCard(movie = a, onClick = { onClick(a) })
                }
            }
            item {
                if (movies.loadState.append is LoadState.Loading) {
                    ShimmerEffect()
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(movies: LazyPagingItems<Movie>): Boolean {
    val loadState = movies.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        movies.itemCount == 0 -> {
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        repeat(10) {
            MovieCardShimmerEffect(
                modifier = Modifier.padding(horizontal = 24.dp),
            )
        }
    }
}

@MoviesPreviews
@Composable
internal fun ShimmerEffectListPreview() {
    CodingChallengeTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            ShimmerEffect()
        }
    }
}
