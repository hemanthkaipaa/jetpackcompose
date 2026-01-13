package com.kaipa.jetpackcompose.ilearn.noteApp.model

import java.time.LocalDateTime
import java.util.UUID

data class NoteData(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
