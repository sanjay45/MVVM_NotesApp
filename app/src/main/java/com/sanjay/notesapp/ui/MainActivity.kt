package com.sanjay.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sanjay.notesapp.R
import com.sanjay.notesapp.viewmodel.NoteViewModel
import com.sanjay.notesapp.viewmodel.NoteViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)



        val noteViewModelFactory = NoteViewModelFactory(application)
        noteViewModel = ViewModelProvider(this,noteViewModelFactory)[NoteViewModel::class.java]







    }




}