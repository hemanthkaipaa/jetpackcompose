package com.kaipa.jetpackcompose.ilearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
            Column(modifier = Modifier.fillMaxWidth().height(150.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                bizProfile()
            }
            Separator()

        }
    }

    @Composable
    fun bizProfile(){
        Surface(modifier = Modifier.width(150.dp).height(150.dp)
            .padding(8.dp), shape = CircleShape,
            border = BorderStroke(1.dp,Color.Magenta),
            shadowElevation = 14.dp, tonalElevation = 14.dp
        ) {
            Image(painter = painterResource(id = R.drawable.outline_adb_24),
                modifier = Modifier.padding(16.dp),
                contentDescription = "display image",
            )
        }
    }

    @Composable
    fun Separator(){
        HorizontalDivider(color = Color.LightGray , thickness = 1.dp)
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview(){
        MainSurface()
    }
}
