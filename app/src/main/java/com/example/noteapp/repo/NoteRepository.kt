package com.example.noteapp.repo

import androidx.lifecycle.LiveData
import com.example.noteapp.data.local.NoteDao
import com.example.noteapp.models.Note

class NoteRepository(private val noteDao: NoteDao) {

    fun getAllNotes(): LiveData<List<Note>> = noteDao.getAllNotes()

    fun searchNote(query: String): LiveData<List<Note>> = noteDao.searchNote(query)

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)


}