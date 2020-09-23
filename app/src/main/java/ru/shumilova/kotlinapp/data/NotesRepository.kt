package ru.shumilova.kotlinapp.data

import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.data.provider.DataProvider

class NotesRepository (private val dataProvider: DataProvider){

    fun getCurrentUser() = dataProvider.getCurrentUser()
    fun getNotes() = dataProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteById(id: String) = dataProvider.getNoteById(id)
}