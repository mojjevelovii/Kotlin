package ru.shumilova.kotlinapp.screen.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note.*
import ru.shumilova.kotlinapp.R
import ru.shumilova.kotlinapp.data.entity.Color
import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.extensions.getResource
import ru.shumilova.kotlinapp.screen.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*


class NoteActivity : BaseActivity<Note?, NoteViewState>() {
    companion object {
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"
        private const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"

        fun getStartIntent(context: Context, noteId: String?): Intent {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, noteId)
            return intent
        }
    }

    override val layoutRes: Int = R.layout.activity_note

    private var note: Note? = null
    override val viewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    private val textChangeListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            triggerSaveNote()
        }
    }

    private fun triggerSaveNote() {
        if (tiet_title.text.isNullOrEmpty()) return

        tiet_title.post {
            note = note?.copy(title = tiet_title.text.toString(),
                    text = et_note_body.text.toString(),
                    lastChanged = Date()) ?: createNewNote()

            note?.let { viewModel.saveChanges(it) }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val noteId = intent.getStringExtra(EXTRA_NOTE)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        noteId?.let {
            viewModel.loadNote(it)
        } ?: let {
            supportActionBar?.title = getString(R.string.new_note_title)
            initView()
        }
    }

    private fun initView() {
        tiet_title.removeTextChangedListener(textChangeListener)
        et_note_body.removeTextChangedListener(textChangeListener)

        note?.let {
            tiet_title.setTextKeepState(it.title)
            et_note_body.setTextKeepState(it.text)
            val color = it.color.getResource()
            tb_toolbar.setBackgroundColor(ContextCompat.getColor(this, color))
        }

        tiet_title.addTextChangedListener(textChangeListener)
        et_note_body.addTextChangedListener(textChangeListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun createNewNote(): Note =
            Note(UUID.randomUUID().toString(),
                    et_note_body.text.toString(),
                    tiet_title.text.toString(),
                    Color.VIOLET,
                    Date())


    override fun renderData(data: Note?) {
        this.note = data
        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_TIME_FORMAT,
                    Locale.getDefault()).format(note!!).format(note!!.lastChanged)
        } ?: getString(R.string.new_note_title)
        initView()
    }

}