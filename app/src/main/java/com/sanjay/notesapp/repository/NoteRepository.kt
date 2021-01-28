package com.sanjay.notesapp.repository


import androidx.lifecycle.LiveData
import com.sanjay.notesapp.dao.NoteDao
import com.sanjay.notesapp.database.NoteDatabase
import com.sanjay.notesapp.model.Note

class NoteRepository(private val noteDatabase: NoteDatabase) {
    
    private val noteDao = noteDatabase.getNoteDao()

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note:Note) {
        noteDao.deleteNote(note)
    }

    fun searchNote(query: String?): LiveData<List<Note>> {
        return noteDao.searchNote(query)
    }
}