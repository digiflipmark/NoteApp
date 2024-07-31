package com.todo.noteapp.di

import android.app.Application
import androidx.room.Room
import com.todo.noteapp.feature_note.data.data_source.NoteDatabase
import com.todo.noteapp.feature_note.data.repository.NoteRepositoryImpl
import com.todo.noteapp.feature_note.domain.repository.NoteRepository
import com.todo.noteapp.feature_note.domain.usecase.AddNote
import com.todo.noteapp.feature_note.domain.usecase.DeleteNote
import com.todo.noteapp.feature_note.domain.usecase.GetNote
import com.todo.noteapp.feature_note.domain.usecase.GetNotes
import com.todo.noteapp.feature_note.domain.usecase.NoteUseCase
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
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository),
            getNotes = GetNotes(repository)
        )
    }

}