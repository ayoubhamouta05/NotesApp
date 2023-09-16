package com.example.notesapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.adapter.NotesAdapter
import com.example.notesapp.databinding.FragmentNotesListBinding
import com.example.notesapp.ui.activities.MainActivity
import com.example.notesapp.viewModel.NoteViewModel


class NotesListFragment : Fragment(R.layout.fragment_notes_list) {
    private lateinit var binding: FragmentNotesListBinding
    private lateinit var notesadapter : NotesAdapter
    private lateinit var viewModel : NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        lifecycleScope.launchWhenStarted {
            viewModel.notes.collect{
                notesadapter.differ.submitList(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.searchNotes.collect{
                notesadapter.differ.submitList(it)
            }
        }
        binding.edSearch.addTextChangedListener {
            viewModel.searchNotes(it.toString().trim())
        }
        binding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment)
        }

        notesadapter.onClick = {
            val bundle = Bundle().apply {
                putSerializable("note",it)
            }

            findNavController().navigate(R.id.action_notesListFragment_to_noteFragment,bundle)
        }

    }

    private fun setupRecyclerView() {
        notesadapter = NotesAdapter()
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesadapter
        }
    }


}