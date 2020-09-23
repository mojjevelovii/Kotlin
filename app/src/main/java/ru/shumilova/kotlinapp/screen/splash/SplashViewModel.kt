package ru.shumilova.kotlinapp.screen.splash

import androidx.lifecycle.Observer
import ru.shumilova.kotlinapp.data.NotesRepository
import ru.shumilova.kotlinapp.data.entity.User
import ru.shumilova.kotlinapp.data.errors.NoAuthException
import ru.shumilova.kotlinapp.screen.base.BaseViewModel


class SplashViewModel() : BaseViewModel<Boolean?, SplashViewState>() {

    private val userObserver = Observer<User?> { userResult ->
        viewStateLiveData.value = when {
            userResult != null -> SplashViewState(authenticated = true)
            else -> SplashViewState(error = NoAuthException())
        }
    }

    fun requestUser() {
        NotesRepository.getCurrentUser().observeForever(userObserver)
    }

    override fun onCleared() {
        NotesRepository.getCurrentUser().removeObserver(userObserver)
    }
}