package com.jeffersonalvess.gists.extensions

import android.view.View
import com.google.android.material.chip.Chip

fun Chip.updateTextOrFallback(text: String?) {
    if (!text.isNullOrBlank()) {
        this.text = text
    } else {
        this.visibility = View.INVISIBLE
    }
}