package com.example.notesapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.repository.NoteRepo


class NoteViewModelProviderFactory(
    private val noteRepo : NoteRepo
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(noteRepo) as T
    }
}