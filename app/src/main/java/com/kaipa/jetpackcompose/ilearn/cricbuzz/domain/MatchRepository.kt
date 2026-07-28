package com.kaipa.jetpackcompose.ilearn.cricbuzz.domain

import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    fun getMatches(matchType: MatchType): Flow<List<Match>>
}