package com.example.notesapp.data


import androidx.room.Entity
import androidx.room.PrimaryKey

import java.io.Serializable

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId : Int ,
    var noteTitle : String,
    var noteText : String ,
) : Serializable