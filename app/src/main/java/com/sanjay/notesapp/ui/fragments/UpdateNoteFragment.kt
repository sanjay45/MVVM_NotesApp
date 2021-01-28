package com.sanjay.notesapp.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.sanjay.notesapp.R
import com.sanjay.notesapp.model.Note
import com.sanjay.notesapp.ui.MainActivity
import com.sanjay.notesapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_update_note.*


class UpdateNoteFragment : Fragment() {

    private val args: UpdateNoteFragmentArgs by navArgs()
    private lateinit var currentNote: Note
    private lateinit var noteViewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_note, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

          noteViewModel = (activity as MainActivity).noteViewModel
          currentNote = args.note!!

        etNoteTitleUpdate.setText(currentNote.noteTitle)
        etNoteBodyUpdate.setText(currentNote.noteBody)

        fab_done.setOnClickListener {

            val noteTitle = etNoteTitleUpdate.text.toString().trim()
            val noteBody = etNoteBodyUpdate.text.toString().trim()

            if(noteTitle.isNotEmpty()){
                val note = Note(currentNote.id,noteTitle,noteBody)
                noteViewModel.updateNote(note)
                Snackbar.make(view,
                    "Note Updated Successfully",
                    Snackbar.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)


            }
            else {
                Toast.makeText(activity,"Enter a note title please",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteNote() {

        AlertDialog.Builder(activity).apply {
            setTitle("Delete Note")
            setMessage("Are you sure you want to permanently delete this note?")
            setPositiveButton("DELETE") { _, _ ->
                noteViewModel.deleteNote(currentNote)
                view?.findNavController()?.navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            setNegativeButton("CANCEL", null)
        }.create().show()

        }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                deleteNote()
            }
        }

        return super.onOptionsItemSelected(item)
    }



}