package com.example.noteapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat


@Parcelize
@Entity(tableName = "notes")
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val content: String,
    val date: Long,
    val color: Int = -1
) : Parcelable {
    val getDate : String
        get() {
            return DateFormat.getDateTimeInstance().format(date)
        }

}
