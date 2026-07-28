package com.kaipa.jetpackcompose.ilearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DemoActivity : ComponentActivity(){
    var _searchList = MutableStateFlow<List<String>>(emptyList())
    val  searchList : StateFlow<List<String>> = _searchList.asStateFlow()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MaterialTheme{
                MainSurface()
            }
        }
    }

    @Composable fun MainSurface(){
        var searchText by remember{mutableStateOf("")}
        val scope = rememberCoroutineScope()
        val list by searchList.collectAsStateWithLifecycle()
        Scaffold(modifier = Modifier.fillMaxSize()) {
            padding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding),
                verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally ) {
                SearchBar(searchText, onTextChange = {searchText = it})
                SearchedText(searchText)
                ButtonAdd(searchText){
                    val text = searchText
                    scope.launch {
                        addSearchText(text)
                    }
                    searchText=""
                }
                ListView(list.distinct())
            }
        }
    }

    @Composable fun SearchBar(text:String, onTextChange:(String)->Unit){

        TextField(
            value = text,
            onValueChange = onTextChange,
            label = {Text("search")},
            placeholder = {Text("Enter")},
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable fun SearchedText(text:String){
        val sometext = text.ifEmpty { "No text found" }
        Text(text = sometext, modifier = Modifier.fillMaxWidth().padding(12.dp))
    }

    @Composable fun ButtonAdd(searchText:String,onAdd:()->Unit){
        Button(onClick = onAdd) {
            Text(text = "Add Text")
        }
    }

    suspend fun addSearchText(searchText: String){
        if(searchText.isEmpty()||searchText.equals("No text found" ))return
        else{
            _searchList.value = _searchList.value + searchText
        }
    }

    @Composable fun ListView(list : List<String>){
        LazyColumn(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)) {

            items(list){
                item ->
                Text(text = item)
            }
        }
    }

    @Composable
    @Preview
    fun DefaultPreview(){
        MainSurface()
    }
}