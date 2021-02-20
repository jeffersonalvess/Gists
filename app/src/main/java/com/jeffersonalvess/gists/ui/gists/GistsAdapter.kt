package com.jeffersonalvess.gists.ui.gists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.jeffersonalvess.gists.R
import com.jeffersonalvess.gists.databinding.GistListItemBinding
import com.jeffersonalvess.gists.extensions.networkImage
import com.jeffersonalvess.gists.extensions.updateTextOrFallback
import com.jeffersonalvess.network.dto.Gist

class GistsAdapter(
    private val clickCallback: (Gist) -> Unit
) : PagedListAdapter<Gist, GistsAdapter.GistViewHolder>(gistDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = GistListItemBinding.inflate(layoutInflater, parent, false)
        return GistViewHolder(view)
    }

    override fun onBindViewHolder(holder: GistViewHolder, position: Int) {
        getItem(position)?.let { gist ->
            holder.bind(gist)
            holder.itemView.setOnClickListener { clickCallback(gist) }
        }
    }

    class GistViewHolder(private val binding: GistListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gist: Gist) {
            binding.avatar.networkImage(
                gist.owner.avatar,
                R.drawable.gist_placeholder
            )

            binding.name.updateTextOrFallback(
                gist.owner.login,
                itemView.context.getString(R.string.name_fallback)
            )

            gist.files.entries.map {
                Chip(itemView.context).apply {
                    text = it.value.type
                }
            }.forEach {
                if (binding.types.childCount == 0) {
                    binding.types.addView(it)
                }
            }
        }
    }

    companion object {
        private val gistDiffCallback = object : DiffUtil.ItemCallback<Gist>() {
            override fun areItemsTheSame(oldItem: Gist, newItem: Gist) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Gist, newItem: Gist) =
                oldItem == newItem
        }
    }
}