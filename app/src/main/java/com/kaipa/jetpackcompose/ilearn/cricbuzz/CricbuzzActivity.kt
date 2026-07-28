package com.kaipa.jetpackcompose.ilearn.cricbuzz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kaipa.jetpackcompose.ilearn.cricbuzz.domain.MatchRepository
import com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation.HomePager
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class CricbuzzActivity : ComponentActivity() {

    @Inject
    lateinit var repository: MatchRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    HomePager(repository)
                }
            }
        }
    }



    @Preview
    @Composable
    fun DefaultPreview() {
        HomePager(repository)
    }
}