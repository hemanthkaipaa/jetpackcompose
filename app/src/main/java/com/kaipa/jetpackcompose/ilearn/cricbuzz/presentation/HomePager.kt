package com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.Match
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.MatchRepository
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.MatchType
import kotlinx.coroutines.launch

private val colorCyan = Color(0xFF00A96E)
private val colorWhite = Color.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePager(repository: MatchRepository){
    val tabs = MatchType.entries
    val pagerState = rememberPagerState(initialPage = 0){
        tabs.size // pageCount
    }

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = {
            Text(text = "Cricbuzz")
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor =colorCyan,
            titleContentColor = colorWhite) )

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = colorCyan,
            contentColor = colorWhite
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = pagerState.currentPage == index,{
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                    text = {
                        Text(
                            text = title.name,
                            fontSize = 15.sp,
                            fontWeight = if(pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }
        }

        // horizontal pager
        HorizontalPager(state = pagerState,Modifier.weight(1f)) {page ->
            val type = tabs[page]
            val viewModel: MatchesViewModel = viewModel(
                key = type.name,
                factory = MatchesViewModelFactory(type, repository)
            )
            MatchScreen(viewModel)
        }
    }
}
@Composable
fun MatchScreen(viewModel: MatchesViewModel){
    android.util.Log.d("CRIC", "MatchScreen composing, vm=$viewModel")
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    android.util.Log.d("CRIC", "state: loading=${state.isLoading} matches=${state.matches.size}")

    when{
        state.isLoading -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }

        state.isError!=null -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = state.isError!!, fontSize = 25.sp)
        }
        else ->
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.matches){
                    MatchCard(it)
                    HorizontalDivider(Modifier.fillMaxWidth().padding(2.dp), thickness = 1.dp, color = Color.LightGray)
                }
            }

    }
}

@Composable
fun MatchCard(match : Match){
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = match.header, fontSize = 18.sp)
        Spacer(Modifier.height(6.dp))
        TeamRow(match.teamA.shortName, match.teamA.score?.toString())
        Spacer(Modifier.height(4.dp))
        TeamRow(match.teamB.shortName,match.teamB.score?.toString())
        Spacer(Modifier.height(8.dp))
        Text(match.status, fontSize = 14.sp, color = Color.Red)
    }
}



@Composable
fun TeamRow(name : String, score: String?){
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
        Text(text = name, Modifier.weight(1f), fontSize = 16.sp)
        score?.let{
            Text(text = score, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}