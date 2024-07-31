package com.todo.noteapp.feature_note.presentation.add_edit_notes

data class NoteTextFiledState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
