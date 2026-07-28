package com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.GetMatchesUseCase
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.MatchRepository
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.MatchType
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MatchesViewModel @Inject constructor(useCase: GetMatchesUseCase,matchType: MatchType) : ViewModel() {

    val uiState: StateFlow<MatchesUiState> = useCase(matchType).map { matches ->
        MatchesUiState(isLoading = false, matches = matches)
    }.catch {
        emit(MatchesUiState(isLoading = false, isError = it.message))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MatchesUiState(isLoading = true)
    )
}
class MatchesViewModelFactory(private val matchType: MatchType, repository: MatchRepository) : ViewModelProvider.Factory {

    private val getMatchesUseCase = GetMatchesUseCase(repository)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST") return MatchesViewModel(getMatchesUseCase, matchType) as T
    }
}