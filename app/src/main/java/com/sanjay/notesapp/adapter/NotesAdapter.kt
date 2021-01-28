package com.sanjay.notesapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sanjay.notesapp.R
import com.sanjay.notesapp.model.Note
import com.sanjay.notesapp.ui.fragments.HomeFragmentDirections
import com.sanjay.notesapp.utils.ColorPicker
import kotlinx.android.synthetic.main.each_note.view.*

class NotesAdapter: RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    }

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
             return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
          return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_note,parent,false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
       val currentNote = differ.currentList[position]
        holder.itemView.apply {
            title.text = currentNote.noteTitle
            description.text = currentNote.noteBody
            val color = Color.parseColor(ColorPicker.getColor())
            ib_color.setBackgroundColor(color)

        }.setOnClickListener{ view ->
           val direction = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currentNote)
            view.findNavController().navigate(direction)
        }


    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}