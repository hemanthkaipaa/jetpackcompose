package com.kaipa.jetpackcompose.ilearn.cricbuzz.data.mapper

import com.kaipa.jetpackcompose.ilearn.cricbuzz.data.remote.dto.MatchDto
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.model.Match
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.model.Team

fun MatchDto.toDomain() : Match{
    return Match(
        id = matchId,
        series = seriesName,
        team1 = Team(
            name = teamAName,
            shortName = teamAShort,
            score = teamAScore,
            overs = teamAOvers
        ),
        team2 = Team(
            name = teamBName,
            shortName = teamBShort,
            score = teamBScore,
            overs = teamBOvers
        ),
        status = matchStatus,
        venue = groundName,
    )
}