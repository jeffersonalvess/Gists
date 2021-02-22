package com.jeffersonalvess.domain.repository

import androidx.lifecycle.LiveData
import com.jeffersonalvess.database.entities.Favorites

interface FavoritesRepository {

    fun getAllFavorites(): LiveData<List<Favorites>>

    fun addFavorite(favorite: Favorites)

    fun removeFavorite(favorite: Favorites)
}