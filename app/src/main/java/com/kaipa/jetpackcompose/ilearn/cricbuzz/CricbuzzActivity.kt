package com.kaipa.jetpackcompose.ilearn.cricbuzz

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kaipa.jetpackcompose.ilearn.cricbuzz.navigation.CricbuzzNavGraph
import com.kaipa.jetpackcompose.ilearn.cricbuzz.presentation.livematches.MatchesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CricbuzzActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {                              // or your app's ILearnTheme { }
                Surface(modifier = Modifier.fillMaxSize()) {
                    CricbuzzNavGraph()
                }
            }
        }
    }
    @Preview
    @Composable
    fun DefaultPreview() {
        CricbuzzNavGraph()
    }
}