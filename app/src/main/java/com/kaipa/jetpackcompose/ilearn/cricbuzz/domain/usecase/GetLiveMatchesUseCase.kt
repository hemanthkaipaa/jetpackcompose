package com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.usecase

import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.model.Match
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.repository.MatchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLiveMatchesUseCase @Inject constructor(val repository: MatchRepository) {
    operator fun invoke() : Flow<List<Match>> = repository.getLiveMatches()
}