package ru.shumilova.kotlinapp.screen.main

import androidx.lifecycle.Observer
import ru.shumilova.kotlinapp.data.NotesRepository
import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.data.model.NoteResult
import ru.shumilova.kotlinapp.screen.base.BaseViewModel


class MainViewModel() : BaseViewModel<List<Note>, MainViewState>() {

    private val repositoryNotes = NotesRepository.getNotes()
    private val notesObserver = Observer<NoteResult> { result ->
        result ?: return@Observer
        when (result) {
            is NoteResult.Success<*> -> viewStateLiveData.value = MainViewState(notes = result.data as? List<Note> ?: emptyList())
            is NoteResult.Error -> viewStateLiveData.value = MainViewState(error = result.error)
        }
    }

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        super.onCleared()
        repositoryNotes.removeObserver(notesObserver)
    }

}