package com.example.mynotes

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.mynotes.data.local.db.NoteDatabase
import com.example.mynotes.data.local.entities.NoteDao
import com.example.mynotes.data.repositories.NoteRepository
import com.example.mynotes.ui.AppMyNotesState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDao(application: Application): NoteDao {
        val db = Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            "note_db"
        ).build()
        return db.dao
    }


    @Provides
    @Singleton
    fun provideRepository(dao: NoteDao): NoteRepository {
        return NoteRepository(dao)
    }

}