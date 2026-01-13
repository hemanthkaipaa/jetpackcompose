package com.kaipa.jetpackcompose.ilearn.noteApp.data

import com.kaipa.jetpackcompose.ilearn.noteApp.model.NoteData

class NoteDataSource {
    fun loadNotes() : List<NoteData>{
        return listOf(
            NoteData(title = "Android", description = "Android Development learning endless for updates in android"),
            NoteData(title = "Shopping", description = "Anniversary shopping along with multiple gifts"),
            NoteData(title = "Sports", description = "A good warm day to go outdoor sports"),
            NoteData(title = "TODO for today", description = "Work on support tickets with end less unknown log analysis")
        )
    }
}