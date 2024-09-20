package com.solodev.codingchallenge.presentation.navgraph

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.solodev.codingchallenge.MainViewModel
import com.solodev.codingchallenge.domain.model.Movie
import com.solodev.codingchallenge.presentation.navgraph.component.BottomNavigationItem
import com.solodev.codingchallenge.presentation.navgraph.component.MoviesBottomNavigation
import com.solodev.codingchallenge.presentation.screens.bookmark.BookmarkScreen
import com.solodev.codingchallenge.presentation.screens.bookmark.BookmarkViewModel
import com.solodev.codingchallenge.presentation.screens.details.DetailScreen
import com.solodev.codingchallenge.presentation.screens.details.DetailsEvent
import com.solodev.codingchallenge.presentation.screens.details.DetailsViewModel
import com.solodev.codingchallenge.presentation.screens.home.HomeScreen
import com.solodev.codingchallenge.presentation.screens.home.HomeViewModel
import com.solodev.codingchallenge.presentation.screens.search.SearchScreen
import com.solodev.codingchallenge.presentation.screens.search.SearchViewModel
import kotlinx.coroutines.delay

@Composable
fun MoviesNavigator(
    onNavigate: (String) -> Unit,
) {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = Icons.Filled.Home, text = "Home"),
            BottomNavigationItem(icon = Icons.Filled.Search, text = "Search"),
            BottomNavigationItem(icon = Icons.Filled.Bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value

    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    val mainViewModel: MainViewModel = hiltViewModel()

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    val isBottomNavBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    val lastRoute = mainViewModel.getLastRoute()

    LaunchedEffect(key1 = Unit) {
        delay(300L)
        if (lastRoute.isNotEmpty() && lastRoute != Route.MoviesNavigation.route) {
            navigateToTap(
                navController = navController,
                route = lastRoute,
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomNavBarVisible) {
                MoviesBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> {
                                navigateToTap(
                                    navController = navController,
                                    route = Route.HomeScreen.route,
                                )
                                onNavigate(Route.BookmarkScreen.route)
                            }

                            1 -> {
                                navigateToTap(
                                    navController = navController,
                                    route = Route.SearchScreen.route,
                                )
                                onNavigate(Route.BookmarkScreen.route)
                            }

                            2 -> {
                                navigateToTap(
                                    navController = navController,
                                    route = Route.BookmarkScreen.route,
                                )
                                onNavigate(Route.BookmarkScreen.route)
                            }
                        }
                    },
                )
            }
        },
    ) {
        val bottomPadding = it.calculateBottomPadding()

        NavHost(
            modifier = Modifier.padding(bottom = bottomPadding),
            navController = navController,
            startDestination = Route.HomeScreen.route,
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val bookmarkViewModel: BookmarkViewModel = hiltViewModel()
                val movies = viewModel.movies.collectAsLazyPagingItems()
                val state = bookmarkViewModel.state.value

                HomeScreen(
                    movies = movies,
                    navigateToSearch = {
                        navigateToTap(navController = navController, Route.SearchScreen.route)
                    },
                    navigateToDetails = { movie ->
                        navigateToDetails(navController = navController, movie = movie)
                    },
                    bookmarkState = state,
                    onNavigate = onNavigate
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value

                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { movie ->
                        navigateToDetails(
                            navController = navController,
                            movie = movie,
                        )
                    },
                    onNavigate = onNavigate
                )
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Movie?>("movie")
                    ?.let { movie ->
                        DetailScreen(
                            movie = movie,
                            event = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            },
                        )
                    }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value

                BookmarkScreen(
                    state = state, navigateToDetails = { movie ->
                        navigateToDetails(
                            navController = navController,
                            movie = movie,
                        )
                    },
                    onNavigate = onNavigate
                )
            }
        }
    }
}

fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, movie: Movie) {
    navController.currentBackStackEntry?.savedStateHandle?.set("movie", movie)
    navController.navigate(route = Route.DetailsScreen.route)
}
