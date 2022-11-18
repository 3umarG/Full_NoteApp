package com.example.noteapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.local.NoteDao
import com.example.noteapp.data.local.NoteDatabase
import com.example.noteapp.models.Note
import com.example.noteapp.repo.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        val noteData: NoteDao = NoteDatabase.getNoteDatabase(application).getDao()
        repository = NoteRepository(noteData)
        viewModelScope.launch {
            delay(3000L)
            _isLoading.value = false
        }
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(note)
    }

    fun searchNote(query: String): LiveData<List<Note>> = repository.searchNote(query)

    fun getAllNotes(): LiveData<List<Note>> = repository.getAllNotes()

}