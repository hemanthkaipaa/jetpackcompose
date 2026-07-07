package com.kaipa.jetpackcompose.ilearn.cricbuzz.data.remote

import com.kaipa.jetpackcompose.ilearn.cricbuzz.data.remote.dto.MatchDto
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.milliseconds

class FakeMatchDataSource @Inject constructor() {
    fun getLiveMatches(): Flow<List<MatchDto>> = flow {
        var runs = 245
        while (true) {
            emit(sampleMatches(runs))
            delay(3000.milliseconds)          // new "scores" every 3 seconds
            runs += 6
        }
    }

    private fun sampleMatches(runs: Int): List<MatchDto> = listOf(
        MatchDto(
            matchId = "1",
            seriesName = "India tour of Australia, 2026",
            teamAName = "India",
            teamAShort = "IND",
            teamAScore = "$runs/4",
            teamAOvers = "42.3",
            teamBName = "Australia",
            teamBShort = "AUS",
            teamBScore = "312/8",
            teamBOvers = "50.0",
            matchStatus = "Live",
            groundName = "MCG, Melbourne"
        ),
        MatchDto(
            matchId = "2",
            seriesName = "England tour of South Africa, 2026",
            teamAName = "South Africa",
            teamAShort = "SA",
            teamAScore = "128/2",
            teamAOvers = "24.1",
            teamBName = "England",
            teamBShort = "ENG",
            teamBScore = null,
            teamBOvers = null,
            matchStatus = "Live",
            groundName = "Newlands, Cape Town"
        )
    )
}