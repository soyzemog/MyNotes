package com.example.mynotes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynotes.data.local.entities.NoteDao
import com.example.mynotes.data.local.entities.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {

    abstract val dao: NoteDao

}

