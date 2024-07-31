package com.todo.noteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todo.noteapp.feature_note.domain.usecase.NoteUseCase
import com.todo.noteapp.feature_note.presentation.util.NoteOrder
import com.todo.noteapp.feature_note.presentation.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteUseCase: NoteUseCase) : ViewModel() {

    private val _state = mutableStateOf(NoteState())
    val state: State<NoteState> get() = _state
    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NoteEvent) {
        when (event) {

            is NoteEvent.Order -> {
                if (state.value.noteOrder == event.noteOrder &&
                    state.value.noteOrder.order == event.noteOrder.order
                ){
                    return
                }
                getNotes(event.noteOrder)
            }
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCase.deleteNote.invoke(event.note)
                }
            }
            is NoteEvent.RestoreNote -> {}
            is NoteEvent.ToggleOrderSelection -> {
                _state.value =
                    state.value.copy(isOrderSelectionVisible = !state.value.isOrderSelectionVisible)
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCase.getNotes(noteOrder).onEach { notes ->
            _state.value = state.value.copy(list = notes, noteOrder = noteOrder)
        }.launchIn(viewModelScope)
    }
}