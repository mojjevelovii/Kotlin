package ru.shumilova.kotlinapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val viewStateLiveData = MutableLiveData<String>()
    private val mainModel = MainModel()

    init {
        viewStateLiveData.value = "Hello!"
        mainModel.counterLiveData.observeForever {
            viewStateLiveData.postValue(it.toString())
        }
    }

    fun getViewState(): LiveData<String> = viewStateLiveData

    fun clickCounter() = mainModel.clickCounter()
}