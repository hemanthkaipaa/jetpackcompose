package com.kaipa.jetpackcompose.ilearn.cricbuzz.domain

import kotlinx.coroutines.flow.Flow

class GetMatchesUseCase (private val repository: MatchRepository) {
    operator fun invoke(matchType: MatchType): Flow<List<Match>> {
        return repository.getMatches(matchType)
    }
}