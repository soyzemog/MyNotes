package com.example.mynotes.data.repositories

import com.example.mynotes.data.domain.Note
import com.example.mynotes.data.local.entities.NoteDao
import com.example.mynotes.data.mapper.toDomain
import com.example.mynotes.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepository(
    private val noteDao: NoteDao
) {

    /**
     * devuelve un listado de Flow de notes (conviertiendo de entity a dominio)
     * - uso Flow para q se refresque la info q muestro en pantalla con respecto
     * a lo q tengo en bd (automaticamente)
     * - continua en NotesViewModel
     */
    fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes().map { it.map { noteEntity -> noteEntity.toDomain() } }
    }


    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.toEntity())
    }


    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toEntity())
    }


    suspend fun getNote(idIn: Int): Note {
        val noteEntity = noteDao.getNote(idIn)
        return noteEntity.toDomain()
    }


}