package com.kaipa.jetpackcompose.ilearn.cricbuzz.data.remote.dto

data class MatchDto(
    val matchId: String,
    val seriesName: String,
    val teamAName: String,
    val teamAShort: String,
    val teamAScore: String?,
    val teamAOvers: String?,
    val teamBName: String,
    val teamBShort: String,
    val teamBScore: String?,
    val teamBOvers: String?,
    val matchStatus: String,
    val groundName: String
)