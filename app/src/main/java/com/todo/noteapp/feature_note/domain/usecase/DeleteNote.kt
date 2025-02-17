package com.todo.noteapp.feature_note.domain.usecase

import com.todo.noteapp.feature_note.domain.model.Note
import com.todo.noteapp.feature_note.domain.repository.NoteRepository

class DeleteNote(private val repository: NoteRepository) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}