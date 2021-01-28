package com.sanjay.notesapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sanjay.notesapp.R
import com.sanjay.notesapp.model.Note
import com.sanjay.notesapp.ui.MainActivity
import com.sanjay.notesapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_new_note.*


class NewNoteFragment : Fragment() {

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var mView:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        noteViewModel = (activity as MainActivity).noteViewModel
    }

    private fun saveNote(view: View) {
        val noteTitle = etNoteTitle.text.toString().trim()
        val noteBody = etNoteBody.text.toString().trim()

        if(noteTitle.isNotEmpty()) {
          val note = Note(0,noteTitle,noteBody)
            noteViewModel.insertNote(note)

             Snackbar.make(view,
             "Note Saved Successfully",
             Snackbar.LENGTH_SHORT).show()
             view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)
        }
        else {
            Toast.makeText(activity,"Please enter note title",Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_new_note,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_save -> {
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)

    }




}