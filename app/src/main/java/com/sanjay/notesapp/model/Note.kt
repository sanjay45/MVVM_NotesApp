package com.sanjay.notesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var noteTitle:String,
    var noteBody:String
    ): Serializable {

}
