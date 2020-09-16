package ru.shumilova.kotlinapp.screen.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_note.*
import ru.shumilova.kotlinapp.R
import ru.shumilova.kotlinapp.data.entity.Color
import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.extensions.getResource
import java.text.SimpleDateFormat
import java.util.*


class NoteActivity : AppCompatActivity() {
    companion object {
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"
        private const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"

        fun getStartIntent(context: Context, note: Note?): Intent {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, note)
            return intent
        }
    }

    private var note: Note? = null
    private lateinit var viewModel: NoteViewModel
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
        setContentView(R.layout.activity_note)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = if (note != null) {
            SimpleDateFormat(DATE_TIME_FORMAT,
                    Locale.getDefault()).format(note!!)//.format(note!!.lastChanged)
        } else {
            getString(R.string.new_note_title)
        }

        tiet_title.addTextChangedListener(textChangeListener)
        et_note_body.addTextChangedListener(textChangeListener)
        initView()
    }

    private fun initView() {
        if (note != null) {
            tiet_title.setText(note?.title ?: "")
            et_note_body.setText(note?.text ?: "")
            val color = note?.color?.getResource()
            color?.let {
                tb_toolbar.setBackgroundColor(ContextCompat.getColor(this, it))
            }
        }
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

}