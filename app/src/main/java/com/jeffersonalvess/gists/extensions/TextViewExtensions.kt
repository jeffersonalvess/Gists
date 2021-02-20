package com.jeffersonalvess.gists.extensions

import android.widget.TextView

fun TextView.updateTextOrFallback(text: String?, fallback: String) {
    this.text = if (text.isNullOrBlank()) fallback else text
}

