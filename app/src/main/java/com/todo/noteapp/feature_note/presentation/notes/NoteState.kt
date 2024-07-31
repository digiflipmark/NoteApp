package com.todo.noteapp.feature_note.presentation.notes

import com.todo.noteapp.feature_note.domain.model.Note
import com.todo.noteapp.feature_note.presentation.util.NoteOrder
import com.todo.noteapp.feature_note.presentation.util.OrderType

data class NoteState(
    val list: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSelectionVisible: Boolean = false
)
