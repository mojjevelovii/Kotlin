package ru.shumilova.kotlinapp.screen.note

import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.screen.base.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data, error) {
    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}