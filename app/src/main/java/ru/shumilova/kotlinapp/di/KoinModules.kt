package ru.shumilova.kotlinapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import ru.shumilova.kotlinapp.data.NotesRepository
import ru.shumilova.kotlinapp.data.provider.DataProvider
import ru.shumilova.kotlinapp.data.provider.FirestoreProvider
import ru.shumilova.kotlinapp.screen.main.MainViewModel
import ru.shumilova.kotlinapp.screen.note.NoteViewModel
import ru.shumilova.kotlinapp.screen.splash.SplashViewModel

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single<DataProvider> { FirestoreProvider(get(), get()) }
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}