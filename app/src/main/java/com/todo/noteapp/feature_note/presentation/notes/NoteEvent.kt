package com.todo.noteapp.feature_note.presentation.notes

import com.todo.noteapp.feature_note.domain.model.Note
import com.todo.noteapp.feature_note.presentation.util.NoteOrder

sealed class NoteEvent {

    data class Order(val noteOrder: NoteOrder) : NoteEvent()
    data class DeleteNote(val note: Note) : NoteEvent()
    object RestoreNote : NoteEvent()
    object ToggleOrderSelection : NoteEvent()
}
