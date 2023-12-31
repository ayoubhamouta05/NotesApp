package com.example.notesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.NotesItemBinding


class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    inner class NotesViewHolder(private val binding: NotesItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(note : Note){
            binding.tvNoteTitle.text = note.noteTitle
        }
    }


    private val diffCallback = object : DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            NotesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.bind(note)
        holder.itemView.setOnClickListener {
            onClick?.invoke(note)
        }
    }

    var onClick:((Note)->Unit)? = null

}