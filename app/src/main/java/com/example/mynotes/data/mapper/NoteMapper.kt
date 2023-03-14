package com.example.mynotes.data.mapper

import com.example.mynotes.data.domain.Note
import com.example.mynotes.data.local.entities.NoteEntity

/**
 * convierto el NoteEntity en Note
 */
fun NoteEntity.toDomain(): Note {
    return Note(
        id = this.id ?: throw Exception(),
        title = this.title,
        subtitle = this.subtitle,
        date = this.date,
        image = this.image,
        link = this.link,
        size = this.size,
        color = this.color
    )
}

/**
 * convierto Note en NoteEntity
 */
fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
        date = this.date,
        image = this.image,
        link = this.link,
        size = this.size,
        color = this.color
    )
}