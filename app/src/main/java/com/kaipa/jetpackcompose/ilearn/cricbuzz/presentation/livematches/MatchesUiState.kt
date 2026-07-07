package com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation.livematches

import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.model.Match

sealed interface MatchesUiState {
    data object Loading : MatchesUiState
    data class Success(val matches:List<Match>):MatchesUiState
    data class Error(val message: String) : MatchesUiState
}