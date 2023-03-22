package com.example.mynotes.ui.screens.note

import android.graphics.Bitmap

sealed interface MiscellaneousOptionsAction {
    data class OnPickColorClick(val color: ColorPick) : MiscellaneousOptionsAction
    data class OnAddLinkClick(val showModal: Boolean) : MiscellaneousOptionsAction
    data class OnTextLinkClick(val text: String) : MiscellaneousOptionsAction
    data class OnAddLinkToNoteClick(val url: String) : MiscellaneousOptionsAction
    data class OnAddImageClick(val image: Bitmap) : MiscellaneousOptionsAction
}