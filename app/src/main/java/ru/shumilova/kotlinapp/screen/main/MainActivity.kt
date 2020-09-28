package ru.shumilova.kotlinapp.screen.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.shumilova.kotlinapp.R
import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.screen.base.BaseActivity
import ru.shumilova.kotlinapp.screen.note.NoteActivity
import ru.shumilova.kotlinapp.screen.splash.SplashActivity

class MainActivity : BaseActivity<List<Note>, MainViewState>(), LogoutDialog.LogoutListener {

    companion object {
        fun start(context: Context) = Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
    }

    override val viewModel: MainViewModel by viewModel()
    override val layoutRes: Int = R.layout.activity_main
    private lateinit var adapter: NotesRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        rv_notes.layoutManager = GridLayoutManager(this, 2)
        adapter = NotesRVAdapter(object : NotesRVAdapter.OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })

        rv_notes.adapter = adapter

        fab_add_button.setOnClickListener { openNoteScreen(null) }
    }

    private fun openNoteScreen(note: Note?) {
        val intent = NoteActivity.getStartIntent(this, note?.id)
        startActivity(intent)
    }

    override fun renderData(data: List<Note>) {
        adapter.notes = data
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
            MenuInflater(this).inflate(R.menu.main, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.logout -> showLogoutDialog().let { true }
                else -> false
            }

    fun showLogoutDialog() {
        supportFragmentManager.findFragmentByTag(LogoutDialog.TAG)
                ?: LogoutDialog().show(supportFragmentManager, LogoutDialog.TAG)
    }

    override fun onLogout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    startActivity(Intent(this, SplashActivity::class.java))
                    finish()
                }
    }
}
