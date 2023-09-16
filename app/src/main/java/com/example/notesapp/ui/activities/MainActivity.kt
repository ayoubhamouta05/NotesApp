package com.example.notesapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.R
import com.example.notesapp.data.NoteDatabase
import com.example.notesapp.repository.NoteRepo
import com.example.notesapp.viewModel.NoteViewModel
import com.example.notesapp.viewModel.NoteViewModelProviderFactory


class MainActivity : AppCompatActivity() {
    val viewModel  : NoteViewModel by lazy {
        val database = NoteDatabase.getDatabaseInstance(this)
        val noteRepo = NoteRepo(database)
        val noteProviderfactory = NoteViewModelProviderFactory(noteRepo)
        ViewModelProvider(this,noteProviderfactory)[NoteViewModel :: class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}