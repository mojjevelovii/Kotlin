package ru.shumilova.kotlinapp.screen.main

import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.screen.base.BaseViewState

class MainViewState(val notes: List<Note> = emptyList(), error: Throwable? = null) : BaseViewState<List<Note>>(notes, error)