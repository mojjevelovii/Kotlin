package ru.shumilova.kotlinapp.screen.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import ru.shumilova.kotlinapp.R
import ru.shumilova.kotlinapp.data.entity.Color
import ru.shumilova.kotlinapp.data.entity.Note


class NotesRVAdapter : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(note) {
            itemView.tv_title.text = title
            itemView.tv_text.text = text
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, mapColor(color)))
        }

        private fun mapColor(color: Color) =
                when (color) {
                    Color.WHITE -> R.color.color_white
                    Color.YELLOW -> R.color.color_yellow
                    Color.GREEN -> R.color.color_green
                    Color.BLUE -> R.color.color_blue
                    Color.RED -> R.color.color_red
                    Color.VIOLET -> R.color.color_violet
                    Color.PINK -> R.color.color_pink
                }
    }

}