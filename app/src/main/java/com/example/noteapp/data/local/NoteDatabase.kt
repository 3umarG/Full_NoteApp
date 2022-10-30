package com.example.noteapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.models.Note


@Database(
    entities = [Note::class] ,
    version = 1 ,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getDao() : NoteDao


    companion object {
        @Volatile
        private var INSTANCE : NoteDatabase? = null

        fun getNoteDatabase(context: Context): NoteDatabase {
            if (INSTANCE != null){
                return INSTANCE!!
            }

            synchronized(this){
                val dbBuilder = Room.databaseBuilder(
                    context.applicationContext ,
                    NoteDatabase::class.java ,
                    "Notes_Database"
                ).build()

                INSTANCE = dbBuilder
                return dbBuilder
            }
        }
    }
}