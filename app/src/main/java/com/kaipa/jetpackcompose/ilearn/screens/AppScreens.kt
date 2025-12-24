package com.kaipa.jetpackcompose.ilearn.screens

enum class AppScreens {
    MovieHomeScreen,
    MovieHomeDetailScreen;

    companion object {
        fun fromRoute(route:String?):AppScreens =
            when(route?.substringBefore("/")){
                MovieHomeScreen.name -> MovieHomeScreen
                MovieHomeDetailScreen.name -> MovieHomeDetailScreen
                null -> MovieHomeScreen
                else -> throw IllegalStateException("$route is not a valid screen")
            }
    }
}