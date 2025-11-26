package com.kaipa.jetpackcompose.ilearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
        Card(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
            shape = CardDefaults.elevatedShape,
            colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
            elevation = CardDefaults.elevatedCardElevation(2.dp),
        ){
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                bizProfile()
                Separator()
                addSpace()
                profileSection()
                addSpace()
            }


        }
    }

    @Composable
    fun addSpace(){
        Spacer(modifier = Modifier.height(10.dp))
    }
    @Composable
    fun bizProfile(){
        Surface(modifier = Modifier
            .width(150.dp)
            .height(150.dp)
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

    @Preview
    @Composable
    fun portfolio(){
        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.TopStart){
            Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(4.dp),
                shape = RoundedCornerShape(corner = CornerSize(4.dp)),
                shadowElevation = 4.dp,
                color = Color.White,
                border = BorderStroke(0.1.dp, color = Color.Magenta)
            )
            {
                portfolioContent(data = listOf("project1","project2","project3"))
            }
        }
    }

    @Composable
    fun portfolioContent(data : List<String>){
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(data.size){ item ->
                itemView(data[item])
            }
        }
    }

    @Composable
    fun itemView(text:String){
        Card(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(4.dp),
            shape = CardDefaults.elevatedShape,
            elevation = CardDefaults.elevatedCardElevation(2.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
            )
        {
            Row(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .padding(4.dp), shape = CircleShape,
                    border = BorderStroke(1.dp,Color.Magenta),
                    shadowElevation = 4.dp, tonalElevation = 4.dp
                ) {
                    Image(painter = painterResource(id = R.drawable.outline_adb_24),
                        modifier = Modifier.padding(4.dp),
                        contentDescription = "display image",
                    )
                }
                Column(modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(8.dp)) {
                    Text(text = text)
                    Text(text = "A great Project Indeed")
                }

            }
        }
    }

    @Composable
    fun profileSection(){
        val isButtonClicked = remember { mutableStateOf(false) }
        Column(modifier = Modifier.padding(4.dp)) {
            Text(
                modifier = Modifier.padding(2.dp),
                text = "Miles P.",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Magenta,
                textAlign = TextAlign.Left
            )
            Text(
                modifier = Modifier.padding(2.dp),
                text = "Android compose programmer",
                color = Color.Black,
                textAlign = TextAlign.Left
            )
            Text(
                modifier = Modifier.padding(2.dp),
                text = "@themiles compose",
                color = Color.Black,
                textAlign = TextAlign.Left
            )
        }
        Button(
            modifier = Modifier.padding(4.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor = Color.White,
            ),
            elevation = ButtonDefaults.buttonElevation(8.dp),
            onClick = {
                isButtonClicked.value = !isButtonClicked.value
            },) {
            Text("Portfolio")
        }
        if(isButtonClicked.value){
            portfolio()
        }else{
            Box{}
        }
    }

    //@Preview(showBackground = true)
    @Composable
    fun Preview(){
        MainSurface()
    }
}
