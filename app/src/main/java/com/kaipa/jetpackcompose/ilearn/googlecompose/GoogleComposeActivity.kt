package com.kaipa.jetpackcompose.ilearn.googlecompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaipa.jetpackcompose.ilearn.R

class GoogleComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            MaterialTheme(){
                MainSurface()
            }
        }
    }
    @Composable
    fun MainSurface(){
        Scaffold(modifier = Modifier.fillMaxSize()) {
                padding ->
            Surface(
                modifier = Modifier.padding(padding),
                content = {
                    DisplayList(messageList())
                }
            )

        }
    }

    fun messageList():List<Message>{
        val list = mutableListOf<Message>()
        list.add(Message("Hemanth  ","Hello there..! How are you "))
        list.add(Message("Hemanth  ","Now you can change the background of " +
                "the message content based on isExpanded when we click on a message. " +
                "You will use the clickable modifier to handle click events on the composable. " +
                "Instead of just toggling the background color of the Surface, " +
                "you will animate the background color by gradually modifying its value from "))
        list.add(Message("Hemanth  ","You need to add the following imports to correctly use Kotlin's delegated property syntax (the by keyword). Alt+Enter or Option+Enter adds them for you.\n" +
                "import androidx.compose.runtime.getValue import androidx.compose.runtime.setValue "))
        list.add(Message("Hemanth  ","Congratulations, you’ve finished the Compose tutorial! You’ve built a simple chat screen efficiently showing a list of expandable & animated messages containing an image and texts, designed using Material Design principles with a dark theme included and previews—all in under 100 lines of code! "))
        list.add(Message("Hemanth  ","Hello there..! How are you "))
        list.add(Message("Hemanth  ","Dark theme (or night mode) can be enabled to avoid a bright display especially at night, or simply to save the device battery. Thanks to the Material Design support, Jetpack Compose can handle the dark theme by default. Having used Material Design colors, text and backgrounds will automatically adapt to the dark background. "))
        list.add(Message("Hemanth  ","Hello there..! How are you "))
        list.add(Message("Hemanth  ","Hello there..! How are you "))
        list.add(Message("Hemanth  ","Hello there..! How are you "))
        list.add(Message("Hemanth  ","Hello there..! How are you "))


        return list
    }

    @Composable
    fun DisplayList(messages:List<Message>){
        var expandedIds by remember{ mutableStateOf(setOf<String>()) }
        LazyColumn(state = rememberLazyListState()) {
            items(messages, key = {message -> message.id}){
                message ->
                MessageCard(message,
                    isExpanded = message.id in expandedIds, onToggle = {
                        expandedIds = if(message.id in expandedIds)
                            expandedIds - message.id
                        else
                            expandedIds + message.id
                    }
                )
            }

        }
    }

    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun DefaultPreview(){
        MaterialTheme(){
            MainSurface()
        }
    }
}