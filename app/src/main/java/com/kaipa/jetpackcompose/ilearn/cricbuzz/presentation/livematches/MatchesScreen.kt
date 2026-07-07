package com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation.livematches

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation.livematches.components.MatchCard


@Composable
fun MatchesScreen(viewModel: MatchesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(val state = uiState){
        is MatchesUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is MatchesUiState.Success ->{
            LazyColumn(Modifier.fillMaxSize().padding(12.dp)) {
                items(state.matches) { match ->
                    MatchCard(match = match)
                }
            }
        }

        is MatchesUiState.Error ->{
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(state.message)
            }
        }
    }
}