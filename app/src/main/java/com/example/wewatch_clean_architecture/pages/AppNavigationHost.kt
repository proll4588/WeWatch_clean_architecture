package com.example.wewatch_clean_architecture.pages

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wewatch_clean_architecture.ViewModels.SelectingMovieViewModel

@Composable
fun AppNavigationHost(navController: NavHostController) {
    val selectedViewModel: SelectingMovieViewModel = hiltViewModel()
    NavHost(navController, startDestination = INIT_ROUTE) {
        composable(Routes.MY_MOVIES.route) {
            MyMoviesPage(onNavigationToSearchMoviesPage = {
                navController.navigate(
                    Routes.SEARCH_MOVIES.route
                )
            })
        }
        composable(Routes.SEARCH_MOVIES.route) {
            SearchMoviesPage(
                onNavigateBack = {
                    navController.navigate(Routes.MY_MOVIES.route)
                },
                onNavigateToObserveSearchMoviesPage = { title, year ->
                    navController.navigate(createObservSearchMoviesRoute(title, year))
                },
                selectingViewModel = selectedViewModel
            )
        }
        composable(Routes.OBSERVE_SEARCH_MOVIES.route, arguments = listOf(
            navArgument("title") { defaultValue = "" },
            navArgument("year") { defaultValue = "" }
        )) {
            ObserveSearchMoviesPage(
                onNavigateBack = {
                    navController.navigate(
                        Routes.SEARCH_MOVIES.route,
                    )
                },
                title = it.arguments?.getString("title") ?: "",
                year = it.arguments?.getString("year") ?: "",
                selectingViewModel = selectedViewModel
            )
        }
    }
}

fun createObservSearchMoviesRoute(title: String, year: String): String {
    return "ObserveSearchMoviesPage?year=$year&title=$title"
}