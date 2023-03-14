package com.example.mynotes.data.local.entities

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String?,
    val subtitle: String?,
    val date: String?,
    val image: String?,
    val link: String?,
    val size: Int?,
    val color: Int?
)