package com.example.mynotes.data.domain

import android.graphics.Bitmap
import com.example.mynotes.ui.screens.note.ColorPick


data class Note(
    val id: Int? = null,
    val title: String? = null,
    val subtitle: String? = null,
    val date: String? = null,
    val image: Bitmap? = null,
    val url: String? = null,
    val color: ColorPick? = null
)