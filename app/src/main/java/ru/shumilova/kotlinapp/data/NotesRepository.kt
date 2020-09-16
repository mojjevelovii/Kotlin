package ru.shumilova.kotlinapp.data

import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.data.provider.DataProvider
import ru.shumilova.kotlinapp.data.provider.FirestoreProvider

object NotesRepository {

    private val dataProvider: DataProvider = FirestoreProvider()

    fun getNotes() = dataProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteById(id: String) = dataProvider.getNoteById(id)

}