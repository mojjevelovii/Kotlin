package ru.shumilova.kotlinapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainModel {
    private val _counterLiveData = MutableLiveData<Int>()
    val counterLiveData: LiveData<Int> = _counterLiveData
    private var counter = 0

    fun clickCounter() = _counterLiveData.postValue(++counter)
}