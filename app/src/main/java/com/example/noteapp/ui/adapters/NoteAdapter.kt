package com.example.noteapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils.loadAnimation
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.models.Note
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.textview.MaterialTextView


class NoteAdapter(
    private val listOfNotes: MutableList<Note>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val noteItemTitle: MaterialTextView = view.findViewById(R.id.noteItemTitle)
        private val noteItemContent: TextView = view.findViewById(R.id.noteItemContent)
        private val noteItemDate: MaterialTextView = view.findViewById(R.id.noteItemDate)

        fun bind(note: Note) {
            noteItemContent.text = note.content
            noteItemDate.text = note.date
            noteItemTitle.text = note.title

            val animation = android.view.animation.AnimationUtils.loadAnimation(view.context, android.R.anim.slide_in_left)
            view.startAnimation(animation)
        }


        fun onClick(note : Note){
            view.setOnClickListener {
                listener.onItemClick(note)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_recycler_view_item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listOfNotes[position])
        holder.onClick(listOfNotes[position])
    }

    override fun getItemCount(): Int {
        return listOfNotes.size
    }
}


interface OnItemClickListener {
    fun onItemClick(note: Note)
}