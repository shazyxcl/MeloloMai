package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myapplication.ui.screens.DetailScreen
import com.example.myapplication.ui.screens.HomeScreen
import com.example.myapplication.ui.screens.PlayerScreen
import com.example.myapplication.ui.screens.SearchScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Detail : Screen("detail/{bookId}") {
        fun createRoute(bookId: String) = "detail/$bookId"
    }
    object Player : Screen("player/{videoId}/{episodeTitle}") {
        fun createRoute(videoId: String, episodeTitle: String) = "player/$videoId/$episodeTitle"
    }
}

@Composable
fun MeloloNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToDetail = { bookId ->
                    navController.navigate(Screen.Detail.createRoute(bookId))
                },
                onNavigateToSearch = {
                    navController.navigate(Screen.Search.route)
                }
            )
        }
        
        composable(Screen.Search.route) {
            SearchScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToDetail = { bookId ->
                    navController.navigate(Screen.Detail.createRoute(bookId))
                }
            )
        }
        
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("bookId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            DetailScreen(
                bookId = bookId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToPlayer = { videoId, episodeTitle ->
                    navController.navigate(Screen.Player.createRoute(videoId, episodeTitle))
                }
            )
        }
        
        composable(
            route = Screen.Player.route,
            arguments = listOf(
                navArgument("videoId") { type = NavType.StringType },
                navArgument("episodeTitle") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId") ?: ""
            val episodeTitle = backStackEntry.arguments?.getString("episodeTitle") ?: ""
            PlayerScreen(
                videoId = videoId,
                episodeTitle = episodeTitle,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

