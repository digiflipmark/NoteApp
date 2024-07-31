package com.todo.noteapp.feature_note.presentation.util

sealed class NoteOrder(val order: OrderType) {

    class Title(order: OrderType) : NoteOrder(order)
    class Date(order: OrderType) : NoteOrder(order)
    class Color(order: OrderType) : NoteOrder(order)

    fun copy(orderType: OrderType): NoteOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }
}
