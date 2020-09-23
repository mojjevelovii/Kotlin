package ru.shumilova.kotlinapp.screen.splash

import ru.shumilova.kotlinapp.screen.base.BaseViewState

class SplashViewState(authenticated: Boolean? = null, error: Throwable? = null) : BaseViewState<Boolean?>(authenticated, error)