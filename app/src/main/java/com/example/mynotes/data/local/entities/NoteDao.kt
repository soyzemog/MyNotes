package com.example.mynotes.data.local.entities

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun getNotes(): Flow<List<NoteEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)


    @Delete
    suspend fun deleteNote(note: NoteEntity)


    @Query("SELECT * FROM NoteEntity WHERE id = :idIn")
    suspend fun getNote(idIn: Int): NoteEntity

}