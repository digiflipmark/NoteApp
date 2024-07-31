package com.todo.noteapp.feature_note.domain.usecase

data class NoteUseCase(
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote,
    val getNotes: GetNotes
)
