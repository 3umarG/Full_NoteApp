package com.example.noteapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "notes")
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val content: String,
    val date: String,
    val color: Int = -1
) : Parcelable
