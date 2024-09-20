package com.solodev.codingchallenge.presentation.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.presentation.common.MoviesList
import com.solodev.codingchallenge.presentation.common.SearchBar
import com.solodev.codingchallenge.presentation.navgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Movie) -> Unit,
    onNavigate: (String) -> Unit
) {

    LaunchedEffect(Unit) {
        onNavigate(Route.SearchScreen.route)
    }

    Column(
        modifier = Modifier
            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            .statusBarsPadding()
            .fillMaxSize(),
    ) {
        Text(
            text = "Search",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(24.dp))

        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchMovies) },
        )

        Spacer(modifier = Modifier.height(24.dp))

        state.movies?.let { m ->
            val movie = m.collectAsLazyPagingItems()
            MoviesList(movies = movie, onClick = { navigateToDetails(it) })
        }
    }
}
