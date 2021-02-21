package com.jeffersonalvess.gists.binding

import androidx.core.view.size
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jeffersonalvess.network.dto.Files
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri


@BindingAdapter("fillGroup")
fun ChipGroup.fillGroup(files: Map<String, Files>) {
    if (this.size > 0) {
        return
    }

    files.entries.map { entry ->
        Chip(context).apply {
            text = entry.value.type

            setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(entry.value.url)
                context.startActivity(intent)
            }
        }
    }.forEach { addView(it) }
}
