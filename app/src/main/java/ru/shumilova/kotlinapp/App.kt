package ru.shumilova.kotlinapp

import android.app.Application
import org.koin.android.ext.android.startKoin
import ru.shumilova.kotlinapp.di.appModule
import ru.shumilova.kotlinapp.di.mainModule
import ru.shumilova.kotlinapp.di.noteModule
import ru.shumilova.kotlinapp.di.splashModule

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
    }
}
