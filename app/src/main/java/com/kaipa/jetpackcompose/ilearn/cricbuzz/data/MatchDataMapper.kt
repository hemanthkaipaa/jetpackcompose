// MatchMapper.kt — replace the old one
package com.kaipa.cricbuzz.data.mapper


import com.kaipa.jetpackcompose.ilearn.cricbuzz.data.MatchDto
import com.kaipa.jetpackcompose.ilearn.cricbuzz.data.room.MatchEntity
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.Match
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.MatchType
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.Score
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.Team

// network shape → database row
fun MatchDto.toEntity(type: MatchType): MatchEntity = MatchEntity(
    id = matchId,
    type = type.name,
    seriesName = seriesName,
    group = groupName,
    venue = venueName,
    teamAShort = teamAShort, teamAFull = teamAFull,
    teamARuns = teamARuns, teamAWickets = teamAWickets, teamAOvers = teamAOvers,
    teamBShort = teamBShort, teamBFull = teamBFull,
    teamBRuns = teamBRuns, teamBWickets = teamBWickets, teamBOvers = teamBOvers,
    status = statusText
)

// database row → domain model
fun MatchEntity.toDomain(): Match = Match(
    id = id,
    seriesName = seriesName,
    group = group,
    venue = venue,
    teamA = Team(
        shortName = teamAShort, fullName = teamAFull,
        score = teamARuns?.let { Score(it, teamAWickets ?: 0, teamAOvers ?: 0.0) }
    ),
    teamB = Team(
        shortName = teamBShort, fullName = teamBFull,
        score = teamBRuns?.let { Score(it, teamBWickets ?: 0, teamBOvers ?: 0.0) }
    ),
    status = status
)