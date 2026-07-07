package com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.model

data class Match(
    val id: String,
    val series: String,
    val team1: Team,
    val team2: Team,
    val status: String,      // e.g. "Live", "Innings Break"
    val venue: String
)

data class Team(
    val name: String,
    val shortName: String,
    val score: String?,      // null before batting, e.g. "245/6"
    val overs: String?       // e.g. "48.2"
)