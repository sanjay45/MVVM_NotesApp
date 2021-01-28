package com.sanjay.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sanjay.notesapp.database.NoteDatabase
import com.sanjay.notesapp.model.Note
import com.sanjay.notesapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NoteRepository
     val allNotes: LiveData<List<Note>>

    init {
        val noteDatabase = NoteDatabase.getDatabase(application)
         repository = NoteRepository(noteDatabase)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun searchNote(query: String?): LiveData<List<Note>> {
        return  repository.searchNote(query)
    }
}