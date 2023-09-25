package com.example.notesapp.ui.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNoteBinding
import com.example.notesapp.viewModel.NoteViewModel
import com.example.notesapp.ui.activities.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteFragment : Fragment(R.layout.fragment_note) {
    private lateinit var binding: FragmentNoteBinding
    val args by navArgs<NoteFragmentArgs>()

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
        binding = FragmentNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.note?.let {
            binding.apply {
                edTitle.setText(it.noteTitle)
                edNote.setText(it.noteText)
            }
            binding.imgDeleteNote.visibility=View.VISIBLE
        }
        binding.apply {
            btnSaveNote.setOnClickListener {
                val id = args.note?.noteId ?:0
                val noteTitle = edTitle.text.toString()
                val noteText  = edNote.text.toString()

                com.example.notesapp.data.Note(id, noteTitle, noteText).also {
                    if(noteTitle.isEmpty() && noteText.isEmpty()){
                        Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT)
                            .show()
                        return@setOnClickListener
                    }

                    viewModel.upsertNote(it)
                    findNavController().navigateUp()

                }
            }
        }

        binding.apply {
            imgDeleteNote.setOnClickListener{
                val noteId = args.note!!.noteId
                val noteTitle = edTitle.text.toString()
                val noteText = edNote.text.toString()

                com.example.notesapp.data.Note(noteId, noteTitle, noteText).also{

                    viewModel.deleteNote(it)
                    findNavController().navigateUp()



                }
            }
        }

    }

}
