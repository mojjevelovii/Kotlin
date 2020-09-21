package ru.shumilova.kotlinapp.screen.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ru.shumilova.kotlinapp.data.NotesRepository
import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.data.model.NoteResult
import ru.shumilova.kotlinapp.screen.base.BaseViewModel

class NoteViewModel(private val repository: NotesRepository = NotesRepository) : BaseViewModel<Note?, NoteViewState>() {

    init {
        viewStateLiveData.value = NoteViewState()
    }
    private lateinit var pendingData: LiveData<NoteResult>
    private var pendingNote: Note? = null
    private val singleResult = object: Observer<NoteResult> {
        override fun onChanged(result: NoteResult) {
            when (result) {
                is NoteResult.Success<*> -> viewStateLiveData.value = NoteViewState(result.data as? Note)
                is NoteResult.Error -> viewStateLiveData.value = NoteViewState(error = result.error)
            }
            pendingData.removeObserver(this)
        }
    }

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String) {
        pendingData = repository.getNoteById(noteId)
        pendingData.observeForever(singleResult)
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }
}