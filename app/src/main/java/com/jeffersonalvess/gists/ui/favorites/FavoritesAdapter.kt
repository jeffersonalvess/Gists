package com.jeffersonalvess.gists.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jeffersonalvess.database.entities.Favorites
import com.jeffersonalvess.gists.R
import com.jeffersonalvess.gists.databinding.FavoritesListItemBinding
import com.jeffersonalvess.gists.extensions.networkImage
import com.jeffersonalvess.gists.extensions.updateTextOrFallback

class FavoritesAdapter(
    private val deleteCallback: (Favorites) -> Unit
) : ListAdapter<Favorites, FavoritesAdapter.FavoritesViewHolder>(favoriteGistDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = FavoritesListItemBinding.inflate(layoutInflater, parent, false)
        return FavoritesViewHolder(view, deleteCallback)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        getItem(position)?.let { favorite ->
            holder.bind(favorite)
        }
    }

    class FavoritesViewHolder(
        private val binding: FavoritesListItemBinding,
        private val deleteCallback: (Favorites) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favorites: Favorites) {
            binding.avatar.networkImage(
                favorites.image,
                R.drawable.gist_placeholder
            )

            binding.name.updateTextOrFallback(
                favorites.name,
                itemView.context.getString(R.string.description_fallback)
            )

            binding.remove.setOnClickListener {
                deleteCallback(favorites)
            }
        }
    }

    companion object {
        private val favoriteGistDiffCallback = object : DiffUtil.ItemCallback<Favorites>() {
            override fun areItemsTheSame(oldItem: Favorites, newItem: Favorites) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Favorites, newItem: Favorites) =
                oldItem == newItem
        }
    }
}