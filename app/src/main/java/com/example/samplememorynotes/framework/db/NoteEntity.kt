package com.example.samplememorynotes.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.data.Note

@Entity(tableName = "note")
data class NoteEntity (
    val title: String,
    val content: String,

    @ColumnInfo(name = "creation_date")
    val creationTime: Long,

    @ColumnInfo(name = "update_time")
    val updateTime: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
    ) {

    companion object {
        fun fromNote(note: Note): NoteEntity {
            // id not needed since db itself generates
            return NoteEntity(note.title, note.content, note.creationTime, note.updateTime)
        }
    }

    fun toNote(): Note {
        return Note(title, content, creationTime, updateTime, id)
    }
}

