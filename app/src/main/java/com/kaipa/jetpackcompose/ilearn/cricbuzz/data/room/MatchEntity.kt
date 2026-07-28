package com.kaipa.jetpackcompose.ilearn.cricbuzz.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey val id :String,
    val type: String,
    val seriesName: String,
    val group: String,
    val venue: String,
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
    val status: String
)

