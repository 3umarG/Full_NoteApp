package com.example.noteapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentAddNoteBinding
import com.example.noteapp.models.Note
import com.example.noteapp.ui.viewmodel.NoteViewModel
import com.example.noteapp.ui.viewmodel.NoteViewModelFactory
import java.text.DateFormat

class AddNoteFragment : Fragment() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var noteViewModelFactory: NoteViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        noteViewModelFactory = NoteViewModelFactory(requireActivity().application)
        noteViewModel = ViewModelProvider(this, noteViewModelFactory)[NoteViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBackAddNote.setOnClickListener {
            val action = AddNoteFragmentDirections.actionAddNoteFragmentToNotesFragment()
            findNavController().navigate(action)
        }


        binding.imageSaveAddNote.setOnClickListener {
            val title = binding.etTitleAddNote.text.toString().trimEnd()
            val content = binding.etContentAddNote.text.toString().trimEnd()
            val date: String = DateFormat.getDateTimeInstance().format(System.currentTimeMillis())

            if (title.isNotEmpty() && content.isNotEmpty()) {
                noteViewModel.addNote(Note(0, title, content, System.currentTimeMillis(), 1))
                val action = AddNoteFragmentDirections.actionAddNoteFragmentToNotesFragment()
                findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Please fill all fields !!", Toast.LENGTH_SHORT).show()
            }
        }


    }

}