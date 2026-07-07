package com.kaipa.jetpackcompose.ilearn.cricbuzz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation.livematches.MatchesScreen

object CricbuzzRoutes{
    const val LIVE_MATCHES = "live_matches"
}

@Composable
fun CricbuzzNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(navController = navController, startDestination = CricbuzzRoutes.LIVE_MATCHES){
        composable(CricbuzzRoutes.LIVE_MATCHES) {
            MatchesScreen()
        }
    }
}