package com.example.notesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note:Note)

    @Query("Select * from note where noteTitle like :searchQuery || '%' ")
    fun searchInNoteTilte(searchQuery: String) : Flow<List<Note>>

    @Query("Select * From note ORDER BY noteId DESC")
    fun selectNotes() : Flow<List<Note>>

}