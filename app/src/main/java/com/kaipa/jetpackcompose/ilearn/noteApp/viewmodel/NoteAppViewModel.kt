package com.kaipa.jetpackcompose.ilearn.noteApp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kaipa.jetpackcompose.ilearn.noteApp.data.NoteDataSource
import com.kaipa.jetpackcompose.ilearn.noteApp.model.NoteData

class NoteAppViewModel : ViewModel() {
    private var notesList = mutableStateListOf<NoteData>()

    var title = mutableStateOf("")
        private set
    var note = mutableStateOf("")
        private set
    val noteList : List<NoteData> get() = notesList

    init {
        notesList.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note:NoteData){
        notesList.add(note)
    }

    fun removeNote(note: NoteData){
        notesList.remove(note)
    }
}