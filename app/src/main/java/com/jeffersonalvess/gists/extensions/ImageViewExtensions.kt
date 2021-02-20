package com.jeffersonalvess.gists.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.networkImage(url: String, placeholder: Int) {
    Picasso.get()
        .load(url)
        .error(placeholder)
        .into(this)
}
