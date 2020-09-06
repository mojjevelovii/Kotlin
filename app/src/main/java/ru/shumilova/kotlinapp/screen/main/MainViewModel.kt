package ru.shumilova.kotlinapp.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.shumilova.kotlinapp.data.NotesRepository


class MainViewModel() : ViewModel() {

    private val viewStateLiveData = MutableLiveData<MainViewState>()

    init {
        viewStateLiveData.value = MainViewState(NotesRepository.getNotes())
    }

    fun getViewState(): LiveData<MainViewState> = viewStateLiveData

}