package ru.shumilova.kotlinapp.extensions

import android.content.Context
import androidx.core.content.ContextCompat
import ru.shumilova.kotlinapp.R
import ru.shumilova.kotlinapp.data.entity.Color

fun Color.getColorInt(context: Context) = ContextCompat.getColor(context, getColorResource())


fun Color.getColorResource() = when (this) {
    Color.WHITE -> R.color.color_white
    Color.YELLOW -> R.color.color_yellow
    Color.GREEN -> R.color.color_green
    Color.BLUE -> R.color.color_blue
    Color.RED -> R.color.color_red
    Color.VIOLET -> R.color.color_violet
    Color.PINK -> R.color.color_pink
}