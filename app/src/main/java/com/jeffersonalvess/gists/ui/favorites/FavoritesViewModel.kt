package com.jeffersonalvess.gists.ui.favorites

import androidx.lifecycle.ViewModel
import com.jeffersonalvess.database.entities.Favorites
import com.jeffersonalvess.domain.repository.FavoritesRepository

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val favoritesList = favoritesRepository.getAllFavorites()

    fun deleteFavorite(favorite: Favorites) {
        favoritesRepository.removeFavorite(favorite)
    }
}