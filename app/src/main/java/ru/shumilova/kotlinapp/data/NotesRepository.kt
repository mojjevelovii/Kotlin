package ru.shumilova.kotlinapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.shumilova.kotlinapp.data.entity.Color
import ru.shumilova.kotlinapp.data.entity.Note
import java.util.*

object NotesRepository {
    private val _notesLiveData = MutableLiveData<List<Note>>()
    val notesLiveData: LiveData<List<Note>> = _notesLiveData
    private val notes: MutableList<Note> = mutableListOf(
            Note(
                    UUID.randomUUID().toString(),
                    "Первая заметка",
                    "Текст первой заметки. Не очень длинный, но интересный",
                    Color.BLUE,
                    Date()
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "Вторая заметка",
                    "Текст второй заметки. Не очень длинный, но интересный",
                    Color.GREEN,
                    Date()
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "Третья заметка",
                    "Текст третьей заметки. Не очень длинный, но интересный",
                    Color.PINK,
                    Date()
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "Четвертая заметка",
                    "Текст четвертой заметки. Не очень длинный, но интересный",
                    Color.RED,
                    Date()
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "Пятая заметка",
                    "Текст пятой заметки. Не очень длинный, но интересный",
                    Color.VIOLET,
                    Date()
            ),
            Note(
                    UUID.randomUUID().toString(),
                    "Шестая заметка",
                    "Текст шестой заметки. Не очень длинный, но интересный",
                    Color.YELLOW,
                    Date()
            )
    )

    init {
        _notesLiveData.value = notes
    }

    fun saveNote (note: Note){
        addOrReplace(note)
        _notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note){
        for (i in 0 until notes.size) {
            if (notes[i] == note) {
                notes.set(i, note)
                return
            }
        }
        notes.add(note)
    }

}