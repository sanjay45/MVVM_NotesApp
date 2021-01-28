package com.sanjay.notesapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sanjay.notesapp.R
import com.sanjay.notesapp.adapter.NotesAdapter
import com.sanjay.notesapp.model.Note
import com.sanjay.notesapp.ui.MainActivity
import com.sanjay.notesapp.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),SearchView.OnQueryTextListener{

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var notesAdapter: NotesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel =(activity as MainActivity).noteViewModel
        setUpRecyclerView()

        fabAddNote.setOnClickListener {
          it.findNavController().navigate(R.id.action_homeFragment_to_newNoteFragment)
        }

    }

    private fun setUpRecyclerView() {
        notesAdapter = NotesAdapter()
        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                            2,
                StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
            adapter = notesAdapter
        }
        activity?.let {
             noteViewModel.allNotes.observe(viewLifecycleOwner, Observer { noteList->
                 notesAdapter.differ.submitList(noteList)
                 updateUI(noteList)
             })
        }
    }

    private fun updateUI(noteList: List<Note>){
        if(noteList.isNotEmpty()) {
           cardView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        } else {
            cardView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu, menu)
        val mMenuSearch = menu.findItem(R.id.menu_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled = true
        mMenuSearch.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null){
            searchNote(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null){
            searchNote(newText)
        }
        return true
    }
    private fun searchNote(query: String?){
        val searchQuery = "%$query%"
        noteViewModel.searchNote(searchQuery).observe(
            this, {list ->
                notesAdapter.differ.submitList(list)
            }
        )
    }

}