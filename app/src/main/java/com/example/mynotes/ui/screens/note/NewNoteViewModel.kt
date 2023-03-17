package com.example.mynotes.ui.screens.note

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.domain.Note
import com.example.mynotes.data.repositories.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    /** init {

        viewModelScope.launch {

           val idNote = savedStateHandle.get<Int>("noteId") ?: 0

            if (idNote != 0) {
                // completo campos (edita nota)
            }

        }

    } **/


    fun onColorPickChange(action: MiscellaneousOptionsAction) {
        when (action) {
            is MiscellaneousOptionsAction.OnPickColorClick -> {
                _state.update {
                    it.copy(
                        subtitle = SubtitleNote(colorBox = action.color),
                        miscellaneous = MiscellaneousOptions(selectedOption = action.color)
                    )
                }

            }
        }
    }


    data class UiState(
        val note: Note = Note(),
        val subtitle: SubtitleNote = SubtitleNote(),
        val miscellaneous: MiscellaneousOptions = MiscellaneousOptions()
    )

}


/**
 * - Esta mal, xq creo un nuevo UiState, en vez de actualizar el estado de
        miscellaneous:
 * _state.value = UiState(
    miscellaneous = MiscellaneousOptions(selectedOption = action.color)
    )
 */