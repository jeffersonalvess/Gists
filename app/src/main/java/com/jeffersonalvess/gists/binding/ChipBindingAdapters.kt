package com.jeffersonalvess.gists.binding

import android.content.Intent
import android.net.Uri
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip

@BindingAdapter("openUrl")
fun Chip.openUrl(url: String) {
    setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }
}