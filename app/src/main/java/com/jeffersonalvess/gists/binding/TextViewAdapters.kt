package com.jeffersonalvess.gists.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jeffersonalvess.gists.R
import com.jeffersonalvess.gists.extensions.updateTextOrFallback
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@BindingAdapter("descriptionText")
fun TextView.descriptionText(description: String?) {
    updateTextOrFallback(
        description,
        context.getString(R.string.description_fallback)
    )
}

@BindingAdapter("nameText")
fun TextView.nameText(name: String?) {
    updateTextOrFallback(
        name,
        context.getString(R.string.name_fallback)
    )
}

@BindingAdapter("formattedDate")
fun TextView.formattedDate(date: String?) {
    val zonedDateTime = ZonedDateTime.parse(date)
    val formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(zonedDateTime)
    text = formattedDate.toString()
}
