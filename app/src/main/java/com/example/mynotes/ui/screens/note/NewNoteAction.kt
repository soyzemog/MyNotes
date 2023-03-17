package com.example.mynotes.ui.screens.note

sealed interface NewNoteAction {

    data class OnSubtitleText(val text: String) : NewNoteAction

}