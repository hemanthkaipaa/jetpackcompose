package com.kaipa.jetpackcompose.ilearn.navigation

import android.R.attr.name
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kaipa.jetpackcompose.ilearn.screens.AppScreens
import com.kaipa.jetpackcompose.ilearn.screens.AppScreens.MovieHomeDetailScreen
import com.kaipa.jetpackcompose.ilearn.screens.detail.MovieDetailScreen
import com.kaipa.jetpackcompose.ilearn.screens.home.MovieHomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = AppScreens.MovieHomeScreen.name) {
        composable(route= AppScreens.MovieHomeScreen.name){
            MovieHomeScreen(navController)
        }

        composable(route = MovieHomeDetailScreen.name.plus("/{movie}"),
            arguments = listOf(navArgument(name = "movie"){type = NavType.StringType})
        ) {
            MovieDetailScreen(navController,it.arguments?.getString("movie"))
        }
    }
}
