package com.example.notesapp.repository

import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDatabase


class NoteRepo(
    noteDatabase : NoteDatabase
) {
    private val noteDao = noteDatabase.noteDao

    suspend fun upsertNote(note : Note)= noteDao.upsertNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    fun getNotes()= noteDao.selectNotes()

    fun searchNotes(searchQuery : String) = noteDao.searchInNoteTilte(searchQuery)


}