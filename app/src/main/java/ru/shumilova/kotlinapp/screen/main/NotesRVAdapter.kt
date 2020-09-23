package ru.shumilova.kotlinapp.screen.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note.view.*
import ru.shumilova.kotlinapp.R
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

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(note: Note) = with(note) {
            (itemView as CardView).run {
                tv_title.text = title
                tv_text.text = text

                val color = note.color.getResource()

                setCardBackgroundColor(ContextCompat.getColor(itemView.context, color))
                setOnClickListener { onItemClickListener.onItemClick(note) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }


}