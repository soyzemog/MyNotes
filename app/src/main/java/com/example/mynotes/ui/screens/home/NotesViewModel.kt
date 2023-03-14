package com.example.mynotes.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.domain.Note
import com.example.mynotes.data.repositories.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {

        viewModelScope.launch {

            noteRepository.getNotes().collect {
                if (it.isNotEmpty()) {

                    _state.value = UiState(loading = true)

                    _state.value = UiState(items = it)

                } else {
                    // mostrar msj si bd esta vacia
                    _state.value = UiState(showMessage = "The DB is empty")
                }
            }

        }

    }

    fun deleteNote(note: Note) {
        if (_state.value.items.contains(note)) {
            viewModelScope.launch { noteRepository.deleteNote(note) }
        }
    }

}


data class UiState(
    val loading: Boolean = false,
    val showMessage: String = "",
    var items: List<Note> = emptyList()
)