package com.kaipa.jetpackcompose.ilearn.cricbuzz.data

// MatchDto.kt
data class MatchDto(
    val matchId: String,
    val seriesName: String,
    val groupName: String,
    val venueName: String,
    val teamAShort: String,
    val teamAFull: String,
    val teamARuns: Int?,
    val teamAWickets: Int?,
    val teamAOvers: Double?,
    val teamBShort: String,
    val teamBFull: String,
    val teamBRuns: Int?,
    val teamBWickets: Int?,
    val teamBOvers: Double?,
    val statusText: String
)