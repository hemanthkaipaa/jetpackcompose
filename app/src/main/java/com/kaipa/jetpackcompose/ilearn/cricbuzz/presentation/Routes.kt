package com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation

object Routes {
    const val HOME = "matches/{type}"
    const val DETAIL = "detail/{matchId}"
    fun detail(matchId: String) = "detail/$matchId"
}