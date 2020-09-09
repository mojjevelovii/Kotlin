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
import ru.shumilova.kotlinapp.extensions.getResource


class NotesRVAdapter(private val onItemClickListener: OnItemClickListener)
    : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(note) {
            itemView.tv_title.text = title
            itemView.tv_text.text = text

            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context, color.getResource()))
            itemView.setOnClickListener { onItemClickListener.onItemClick(note) }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }


}