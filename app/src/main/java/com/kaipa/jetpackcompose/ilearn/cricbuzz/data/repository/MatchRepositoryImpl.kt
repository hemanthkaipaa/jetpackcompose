package com.kaipa.jetpackcompose.ilearn.cricbuzz.data.repository

import com.kaipa.jetpackcompose.ilearn.cricbuzz.data.mapper.toDomain
import com.kaipa.jetpackcompose.ilearn.cricbuzz.data.remote.FakeMatchDataSource
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.model.Match
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.repository.MatchRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MatchRepositoryImpl @Inject constructor(private val api: FakeMatchDataSource): MatchRepository{
    override fun getLiveMatches(): Flow<List<Match>> {
        return api.getLiveMatches().map { // this has converted from Flow<List<MatchDto>> to Flow<List<Match>>
            listOfMatches ->
            listOfMatches.map { matchDTO ->
                matchDTO.toDomain()
            }
        }
    }

}