package com.example.mynotes.ui.screens.note

sealed interface MiscellaneousOptionsAction {
    data class OnPickColorClick(val color: ColorPick) : MiscellaneousOptionsAction
}