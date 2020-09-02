package ru.shumilova.kotlinapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val viewStateLiveData = MutableLiveData<String>()

    init {
        viewStateLiveData.value = "Hello!"
    }

    fun getViewState(): LiveData<String> = viewStateLiveData
}