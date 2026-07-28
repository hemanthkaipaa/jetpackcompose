package com.kaipa.jetpackcompose.ilearn.cricbuzz.domain

// adding multiple model classes in a single file as its a small project and not recommended for large

enum class MatchType{
    LIVE,UPCOMING,RECENT
}

data class Score(val runs:Int, val wickets:Int, val overs: Double){
    override fun toString() = "$runs/$wickets ($overs)"
}

data class Team(val shortName:String, val fullName:String, val score:Score?=null)

data class Match(
    val id: String,
    val seriesName: String,
    val group: String,
    val venue: String,
    val teamA: Team,
    val teamB: Team,
    val status: String
){
    val header: String get() = "$group • $venue"
}