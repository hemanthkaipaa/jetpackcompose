package com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation

import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.Match

data class MatchesUiState(
        val isLoading : Boolean = false,
        val isError : String? = null,
        val matches : List<Match> = emptyList()
    )