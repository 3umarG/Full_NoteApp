package com.example.noteapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.databinding.FragmentSaveOrDeleteBinding
import com.example.noteapp.models.Note
import com.example.noteapp.ui.viewmodel.NoteViewModel
import com.example.noteapp.ui.viewmodel.NoteViewModelFactory
import java.text.DateFormat

class SaveOrDeleteFragment : Fragment() {
    private lateinit var binding: FragmentSaveOrDeleteBinding
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteViewModelFactory: NoteViewModelFactory
    private val args by navArgs<SaveOrDeleteFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentSaveOrDeleteBinding.inflate(inflater, container, false)
        noteViewModelFactory = NoteViewModelFactory(requireActivity().application)
        noteViewModel = ViewModelProvider(this, noteViewModelFactory)[NoteViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Binding
        val title = binding.etTitleUpdate
        val content = binding.etContentUpdate
        val date = binding.noteEditedOnUpdate

        // Get the value from args .
        val titleStr: String = args.note.title
        val contentStr: String = args.note.content
        val editedOnStr: String = "Edited on : ${args.note.getDate}"

        // Set these values to Views
        title.setText(titleStr)
        content.setText(contentStr)
        date.text = editedOnStr

        binding.backArrow.setOnClickListener {
           back()
        }
        binding.imageSaveUpdate.setOnClickListener {
            if (title.text.toString().isNotEmpty()
                && content.text.toString().isNotEmpty()
            ) {
                noteViewModel.updateNote(
                    Note(
                        args.note.id,
                        title.text.toString(),
                        content.text.toString(),
                        System.currentTimeMillis()
                    )
                )
                back()

            } else {
                Toast.makeText(requireContext(), "Please fill all fields !!", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }


    private fun back() {
        val action = SaveOrDeleteFragmentDirections.actionSaveOrDeleteFragmentToNotesFragment()
        findNavController().navigate(action)
    }
}