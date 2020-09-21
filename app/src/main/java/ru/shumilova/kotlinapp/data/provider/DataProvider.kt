package ru.shumilova.kotlinapp.data.provider

import androidx.lifecycle.LiveData
import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.data.model.NoteResult

interface DataProvider {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
}