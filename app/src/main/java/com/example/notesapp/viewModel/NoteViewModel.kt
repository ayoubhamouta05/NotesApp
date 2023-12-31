package com.example.notesapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.Note
import com.example.notesapp.repository.NoteRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors


class NoteViewModel(
    private val noteRepo : NoteRepo
) : ViewModel() {
    val notes = noteRepo.getNotes()
    private val _searchNote = MutableStateFlow<List<Note>>(emptyList())
    private val executor = Executors.newSingleThreadExecutor()

    val searchNotes : StateFlow<List<Note>> = _searchNote

//    fun upsertNote(note :Note) = viewModelScope.launch {
//        noteRepo.upsertNote(note)
//    }
//    fun deleteNote(note:Note) = viewModelScope.launch {
//        noteRepo.deleteNote(note)
//    }
    fun upsertNote(note: Note) {
    executor.execute {
        noteRepo.upsertNote(note)
    }
}

    fun deleteNote(note: Note) {
        executor.execute {
            noteRepo.deleteNote(note)
        }
    }


    fun searchNotes (searchQuery : String) = viewModelScope.launch {
        noteRepo.searchNotes(searchQuery).collect{
            _searchNote.emit(it)
        }
    }


}