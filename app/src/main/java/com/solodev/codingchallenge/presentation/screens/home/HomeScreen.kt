package com.solodev.codingchallenge.presentation.screens.home

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.presentation.common.MoviesList
import com.solodev.codingchallenge.presentation.common.MoviesListHorizontal
import com.solodev.codingchallenge.presentation.common.SearchBar
import com.solodev.codingchallenge.presentation.navgraph.Route
import com.solodev.codingchallenge.presentation.screens.bookmark.BookmarkState
import com.solodev.codingchallenge.utils.Constants.TestTags.TITLE_MARQUEES
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    movies: LazyPagingItems<Movie>,
    bookmarkState: BookmarkState,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Movie) -> Unit,
    onNavigate: (String) -> Unit
) {
    val titles by remember {
        derivedStateOf {
            if (movies.itemCount > 10) {
                movies.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " | ") { it.trackName ?: "" }
            } else {
                ""
            }
        }
    }

    var isRefreshing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            scope.launch {
                isRefreshing = true
                delay(500)
                movies.refresh()
                isRefreshing = false
            }
        },
    )

    LaunchedEffect(Unit) {
        onNavigate(Route.HomeScreen.route)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(start = 14.dp, end = 14.dp),
    ) {

        SearchBar(
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
                navigateToSearch()
            },
            onSearch = {},
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (bookmarkState.movies.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "Bookmark",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            MoviesListHorizontal(
                bookmarkedMovies = bookmarkState.movies,
                onClick = {
                    navigateToDetails(it)
                })
        }

        Spacer(modifier = Modifier.height(14.dp))

        TitleMarquees(titles)

        Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
            MoviesList(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .fillMaxWidth(),
                movies = movies,
                onClick = {
                    navigateToDetails(it)
                },
            )

            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
    }
}

@Composable
fun TitleMarquees(titles: String) {
    Text(
        text = titles,
        modifier = Modifier
            .fillMaxWidth()
            .basicMarquee()
            .testTag(TITLE_MARQUEES),
        fontSize = MaterialTheme.typography.bodySmall.fontSize,
        color = MaterialTheme.colorScheme.onSurface,
    )
}