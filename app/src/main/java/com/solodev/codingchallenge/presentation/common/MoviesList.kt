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
        items(count = movies.size) { a ->
            val article = movies[a]
            MovieCard(movie = article, onClick = { onClick(article) })
        }
    }
}

@Composable
fun MoviesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Movie>,
    onClick: (Movie) -> Unit,
) {
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(all = 6.dp),
        ) {
            items(count = articles.itemCount) { article ->
                articles[article]?.let { a ->
                    MovieCard(movie = a, onClick = { onClick(a) })
                }
            }
            item {
                if (articles.loadState.append is LoadState.Loading) {
                    ShimmerEffect()
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<Movie>): Boolean {
    val loadState = articles.loadState
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

        articles.itemCount == 0 -> {
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
