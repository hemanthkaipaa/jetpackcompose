package com.kaipa.jetpackcompose.ilearn.noteApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kaipa.jetpackcompose.ilearn.components.ColumnWrapper
import com.kaipa.jetpackcompose.ilearn.noteApp.model.NoteData
import com.kaipa.jetpackcompose.ilearn.noteApp.viewmodel.NoteAppViewModel
import com.kaipa.jetpackcompose.ilearn.ui.theme.MyApplicationTheme
import com.kaipa.jetpackcompose.ilearn.ui.theme.Purple80

class NoteAppActivity : ComponentActivity() {
    val noteViewModel : NoteAppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                NoteApp(noteViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteApp(noteViewModel: NoteAppViewModel){
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth(), title = {
                Text(text = "Notes App")
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple80))
        },
    ) { paddingValues ->
        ColumnWrapper(orientation = Arrangement.Top, alignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()
            .padding(paddingValues)) {
            Title(noteViewModel.title)
            AddNote(noteViewModel.note)
            Save{
                noteViewModel.addNote(NoteData(title = noteViewModel.title.value, description = noteViewModel.note.value))
                noteViewModel.title.value = ""
                noteViewModel.note.value = ""
            }
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(noteViewModel.noteList){
                    NoteRow( it){
                        noteViewModel.removeNote(it)
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    NoteApp(noteViewModel = NoteAppViewModel())
}