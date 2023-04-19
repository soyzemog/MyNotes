package com.example.mynotes.data.local.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mynotes.ui.screens.note.ColorPick

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String?,
    val subtitle: String?,
    //val date: String?,
    //val image: Bitmap?,
    //val url: String?,
    //val color: ColorPick?
)