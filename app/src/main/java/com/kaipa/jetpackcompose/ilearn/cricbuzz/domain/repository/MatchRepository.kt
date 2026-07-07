package com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.repository

import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.model.Match
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    fun getLiveMatches() : Flow<List<Match>>
}