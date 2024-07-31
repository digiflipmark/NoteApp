package com.todo.noteapp.feature_note.presentation.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending: OrderType()
}
