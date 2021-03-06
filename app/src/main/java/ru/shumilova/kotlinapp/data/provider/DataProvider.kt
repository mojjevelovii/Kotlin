package ru.shumilova.kotlinapp.data.provider

import androidx.lifecycle.LiveData
import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.data.entity.User
import ru.shumilova.kotlinapp.data.model.NoteResult

interface DataProvider {
    fun getCurrentUser(): LiveData<User?>
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun deleteNote(id: String): LiveData<NoteResult>
}