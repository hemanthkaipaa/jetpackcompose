package com.kaipa.jetpackcompose.ilearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaipa.jetpackcompose.ilearn.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainSurface()
            }
        }
    }

    @Composable
    fun MainSurface(){
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.White)
        ){
            // card component
            CardComponent()
        }
    }

    @Composable
    fun CardComponent() {
        Card(modifier = Modifier.fillMaxWidth().fillMaxHeight()
            .padding(16.dp),
            shape = CardDefaults.elevatedShape,
            colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(3.dp),
        ){

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview(){
        MainSurface()
    }
}





