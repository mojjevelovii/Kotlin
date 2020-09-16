package ru.shumilova.kotlinapp.screen.note

import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.screen.base.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)