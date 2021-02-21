package com.jeffersonalvess.gists.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.jeffersonalvess.gists.R
import com.jeffersonalvess.gists.extensions.networkImage

@BindingAdapter("userImage")
fun ImageView.src(url: String) {
    networkImage(url, R.drawable.gist_placeholder)
}