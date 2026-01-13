package com.kaipa.jetpackcompose.ilearn.noteApp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.kaipa.jetpackcompose.ilearn.components.ButtonWrapper
import com.kaipa.jetpackcompose.ilearn.components.ColumnWrapper
import com.kaipa.jetpackcompose.ilearn.components.TextFieldWrapper
import com.kaipa.jetpackcompose.ilearn.noteApp.model.NoteData
import java.time.format.DateTimeFormatter

@Composable
fun Title(mutableStateText: MutableState<String>){
    val keyboard = LocalSoftwareKeyboardController.current
    TextFieldWrapper(
        mutableValue = mutableStateText,
        label = "Title",
        placeholder = "Enter Title",
    ){
        keyboard?.hide()
    }
}

@Composable
fun AddNote(mutableStateText: MutableState<String>){
    val keyboard = LocalSoftwareKeyboardController.current
    TextFieldWrapper(
        mutableValue = mutableStateText,
        label = "Add Note",
        placeholder = "Enter Notes",
    ){
        keyboard?.hide()
    }
}

@Composable
fun Save(onClick : () -> Unit){
    ButtonWrapper(text = "Save",onClick=onClick)
}

@Composable
fun NoteRow(note: NoteData, onClick :() -> Unit){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .clickable {
                onClick()
            },
        color = Color.LightGray
    ) {
        ColumnWrapper(orientation = Arrangement.Center, alignment = Alignment.Start, padding = 8.dp) {
            Text(text=note.title, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(4.dp))
            Text(text=note.description, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(4.dp))
            Text(text=note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")), style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(4.dp))
        }
    }

}