package com.solodev.codingchallenge.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.solodev.codingchallenge.presentation.screens.onboarding.OnboardingScreen
import com.solodev.codingchallenge.presentation.screens.onboarding.OnboardingViewModel

@Composable
fun NavGraph(
    startDestination: String,
    onNavigate: (String) -> Unit,
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnboardingScreen.route,
        ) {
            composable(
                route = Route.OnboardingScreen.route,
            ) {
                val viewModel: OnboardingViewModel = hiltViewModel()

                OnboardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.MoviesNavigation.route,
            startDestination = Route.MoviesScreen.route,
        ) {
            composable(
                route = Route.MoviesScreen.route,
            ) {
                MoviesNavigator(
                    onNavigate = onNavigate)
            }
        }
    }
}
