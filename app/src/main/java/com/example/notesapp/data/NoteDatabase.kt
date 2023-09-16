package com.example.notesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note :: class], version =1, exportSchema = true)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao : NoteDao

    companion object{
        @Volatile
        var INSTANCE : NoteDatabase? =null

        @Synchronized
        fun getDatabaseInstance (context : Context) : NoteDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "notes_db"
                ).fallbackToDestructiveMigration().build()
            }
            return INSTANCE!!
        }

    }



}