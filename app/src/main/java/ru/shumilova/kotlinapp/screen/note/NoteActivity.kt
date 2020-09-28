package ru.shumilova.kotlinapp.screen.note

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.shumilova.kotlinapp.R
import ru.shumilova.kotlinapp.data.entity.Color
import ru.shumilova.kotlinapp.data.entity.Note
import ru.shumilova.kotlinapp.extensions.getColorInt
import ru.shumilova.kotlinapp.screen.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*


class NoteActivity : BaseActivity<NoteViewState.Data, NoteViewState>() {
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
    override val viewModel: NoteViewModel by viewModel()

    var color: Color = Color.WHITE

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            triggerSaveNote()
        }
    }

    private fun triggerSaveNote() {
        if (tiet_title.text.isNullOrEmpty()) return

        tiet_title.post {
            note = note?.copy(
                    title = tiet_title.text.toString(),
                    text = et_note_body.text.toString(),
                    color = color,
                    lastChanged = Date()) ?: createNewNote()

            note?.let { viewModel.saveChanges(it) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?) = menuInflater.inflate(R.menu.note, menu).let { true }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val noteId = intent.getStringExtra(EXTRA_NOTE)
        setSupportActionBar(tb_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        noteId?.let {
            viewModel.loadNote(it)
        } ?: let {
            supportActionBar?.title = getString(R.string.new_note_title)
            initView()
        }
    }

    private fun initView() {
        tiet_title.removeTextChangedListener(textWatcher)
        et_note_body.removeTextChangedListener(textWatcher)

        note?.let {
            tiet_title.setTextKeepState(it.title)
            et_note_body.setTextKeepState(it.text)
            tb_toolbar.setBackgroundColor(it.color.getColorInt(this))
            SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault()).format(it.lastChanged)
        }

        tiet_title.addTextChangedListener(textWatcher)
        et_note_body.addTextChangedListener(textWatcher)

        colorPicker.onColorClickListener = {
            color = it
            tb_toolbar.setBackgroundColor(it.getColorInt(this))
            triggerSaveNote()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.palette -> togglePalete().let { true }
        R.id.delete -> deleteNote().let { true }

        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun togglePalete() {
        if (colorPicker.isOpen) {
            colorPicker.close()
        } else {
            colorPicker.open()
        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(this)
                .setMessage(getString(R.string.delete))
                .setNegativeButton(getString(R.string.note_delete_cancel)) { dialog, which -> dialog.dismiss() }
                .setPositiveButton(getString(R.string.note_delete_ok)) { dialog, which -> viewModel.deleteNote() }
                .show()
    }

    private fun createNewNote(): Note =
            Note(UUID.randomUUID().toString(),
                    et_note_body.text.toString(),
                    tiet_title.text.toString(),
                    Color.VIOLET,
                    Date())


    override fun renderData(data: NoteViewState.Data) {
        if (data.isDeleted) {
            finish()
            return
        }
        this.note = data.note
        initView()
    }

}