package com.example.noteapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.models.Note

@Dao
interface NoteDao {

    // Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note : Note)

    // Update
    @Update
    suspend fun updateNote(note: Note)

    // Delete
    @Delete
    suspend fun deleteNote(note: Note)

    // Get All
    @Query("SELECT * FROM notes ORDER BY date DESC")
    fun getAllNotes() : LiveData<List<Note>>

    // Search on Note by using title / content
    @Query("SELECT * FROM NOTES WHERE title LIKE :query OR content LIKE :query ORDER BY id ASC ")
    fun searchNote(query: String) : LiveData<List<Note>>
}