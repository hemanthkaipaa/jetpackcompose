package com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation.livematches.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.model.Match
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.model.Team

@Composable
fun MatchCard(match: Match) {
    Card(Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(match.series, fontWeight = FontWeight.Bold)
            Text(match.status)
            Spacer(Modifier.height(8.dp))
            TeamRow(match.team1)
            TeamRow(match.team2)
            Spacer(Modifier.height(4.dp))
            Text(match.venue)
        }
    }
}

@Composable
private fun TeamRow(team: Team) {
    Row(Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
        Text(team.shortName, fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 8.dp))
        val score = team.score?.let { s -> "$s (${team.overs ?: "-"})" } ?: "yet to bat"
        Text(score)
    }
}