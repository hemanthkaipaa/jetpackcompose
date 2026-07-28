package com.kaipa.jetpackcompose.ilearn.cricbuzz.data

import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class FakeMatchDataSource @Inject constructor() {
    suspend fun fetchMatchesList(type: String): List<MatchDto> {
        delay(600.milliseconds)
        return when (type) {
            "LIVE"     -> liveDtos()
            "UPCOMING" -> upcomingDtos()
            else       -> recentDtos()
        }
    }
    private fun liveDtos() = listOf(
        MatchDto(
            matchId = "m1",
            seriesName = "ENGLAND DOMESTIC ONE-DAY CUP 2026",
            groupName = "Group A", venueName = "Guildford",
            teamAShort = "SUR", teamAFull = "Surrey",
            teamARuns = 26, teamAWickets = 1, teamAOvers = 8.2,
            teamBShort = "LEIC", teamBFull = "Leicestershire",
            teamBRuns = null, teamBWickets = null, teamBOvers = null,
            statusText = "Leicestershire opt to bowl"
        ),
        MatchDto(
            matchId = "m2",
            seriesName = "ENGLAND DOMESTIC ONE-DAY CUP 2026",
            groupName = "Group B", venueName = "Southampton",
            teamAShort = "MDX", teamAFull = "Middlesex",
            teamARuns = 30, teamAWickets = 0, teamAOvers = 7.0,
            teamBShort = "HAM", teamBFull = "Hampshire",
            teamBRuns = null, teamBWickets = null, teamBOvers = null,
            statusText = "Middlesex opt to bat"
        ),
        MatchDto(
            matchId = "m3",
            seriesName = "ENGLAND DOMESTIC ONE-DAY CUP 2026",
            groupName = "Group B", venueName = "Worcester",
            teamAShort = "DERBY", teamAFull = "Derbyshire",
            teamARuns = 33, teamAWickets = 1, teamAOvers = 9.0,
            teamBShort = "WORCS", teamBFull = "Worcestershire",
            teamBRuns = null, teamBWickets = null, teamBOvers = null,
            statusText = "Worcestershire opt to bowl"
        )
    )

    private fun upcomingDtos() = listOf(
        MatchDto(
            matchId = "u1",
            seriesName = "INDIAN PREMIER LEAGUE 2026",
            groupName = "Match 42", venueName = "Wankhede, Mumbai",
            teamAShort = "MI", teamAFull = "Mumbai Indians",
            teamARuns = null, teamAWickets = null, teamAOvers = null,
            teamBShort = "CSK", teamBFull = "Chennai Super Kings",
            teamBRuns = null, teamBWickets = null, teamBOvers = null,
            statusText = "Today, 7:30 PM"
        ),
        MatchDto(
            matchId = "u2",
            seriesName = "INDIAN PREMIER LEAGUE 2026",
            groupName = "Match 43", venueName = "Chinnaswamy, Bengaluru",
            teamAShort = "RCB", teamAFull = "Royal Challengers",
            teamARuns = null, teamAWickets = null, teamAOvers = null,
            teamBShort = "KKR", teamBFull = "Kolkata Knight Riders",
            teamBRuns = null, teamBWickets = null, teamBOvers = null,
            statusText = "Tomorrow, 3:30 PM"
        )
    )

    private fun recentDtos() = listOf(
        MatchDto(
            matchId = "r1",
            seriesName = "INDIAN PREMIER LEAGUE 2026",
            groupName = "Match 41", venueName = "Eden Gardens, Kolkata",
            teamAShort = "KKR", teamAFull = "Kolkata Knight Riders",
            teamARuns = 189, teamAWickets = 6, teamAOvers = 20.0,
            teamBShort = "RR", teamBFull = "Rajasthan Royals",
            teamBRuns = 172, teamBWickets = 9, teamBOvers = 20.0,
            statusText = "Kolkata Knight Riders won by 17 runs"
        )
    )
}