package com.jeffersonalvess.gists.binding

import android.content.Context
import androidx.core.view.size
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.jeffersonalvess.network.dto.Files
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import com.jeffersonalvess.gists.R


@BindingAdapter(value = ["files", "url"], requireAll = true)
fun ChipGroup.fillGroup(files: Map<String, Files>, url: String) {
    if (this.size > 0) {
        return
    }

    files.entries.map { entry ->
        Chip(context).apply {
            text = entry.value.name
            openUrl(entry.value.url)
        }
    }.forEach { addView(it) }
     .also { addView(getLastChip(url, context)) }
}

private fun getLastChip(url: String, context: Context) =
    Chip(context).apply {
        text = context.getString(R.string.see_gist)
        chipIcon = context.getDrawable(R.drawable.github_logo)
        openUrl(url)
}
