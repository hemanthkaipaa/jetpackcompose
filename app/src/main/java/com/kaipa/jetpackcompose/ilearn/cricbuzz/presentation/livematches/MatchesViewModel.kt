package com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation.livematches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.model.Match
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.usecase.GetLiveMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
@HiltViewModel
class MatchesViewModel @Inject constructor(private val getLiveMatches : GetLiveMatchesUseCase): ViewModel(){
    val uiState : StateFlow<MatchesUiState> =
        getLiveMatches().map{matches -> MatchesUiState.Success(matches) as MatchesUiState }.catch {
            e ->
            emit(MatchesUiState.Error(e.toString()))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MatchesUiState.Loading
        )
}