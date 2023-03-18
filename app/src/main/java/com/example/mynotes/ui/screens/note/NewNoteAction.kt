package com.example.mynotes.ui.screens.note

sealed interface NewNoteAction {
    data class OnTitleText(val text: String) : NewNoteAction
    data class OnSubtitleText(val text: String) : NewNoteAction

}