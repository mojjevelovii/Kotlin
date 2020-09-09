package ru.shumilova.kotlinapp.data

import ru.shumilova.kotlinapp.data.entity.Color
import ru.shumilova.kotlinapp.data.entity.Note

object NotesRepository {
    val notes: List<Note> = listOf(
            Note(
                    "Первая заметка",
                    "Текст первой заметки. Не очень длинный, но интересный",
                    Color.BLUE
            ),
            Note(
                    "Вторая заметка",
                    "Текст второй заметки. Не очень длинный, но интересный",
                    Color.GREEN
            ),
            Note(
                    "Третья заметка",
                    "Текст третьей заметки. Не очень длинный, но интересный",
                    Color.PINK
            ),
            Note(
                    "Четвертая заметка",
                    "Текст четвертой заметки. Не очень длинный, но интересный",
                    Color.RED
            ),
            Note(
                    "Пятая заметка",
                    "Текст пятой заметки. Не очень длинный, но интересный",
                    Color.VIOLET
            ),
            Note(
                    "Шестая заметка",
                    "Текст шестой заметки. Не очень длинный, но интересный",
                    Color.YELLOW
            )
    )

}