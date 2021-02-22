package com.jeffersonalvess.domain.repository

import com.jeffersonalvess.database.dao.FavoriteGistDao
import com.jeffersonalvess.database.entities.Favorites

class FavoritesRepositoryImpl(
    private val favoriteGistDao: FavoriteGistDao
): FavoritesRepository {

    private val favorites by lazy { favoriteGistDao.findAll() }

    override fun getAllFavorites() = favorites

    override fun addFavorite(favorite: Favorites) {
        favoriteGistDao.insert(favorite)
    }

    override fun removeFavorite(favorite: Favorites) {
        favoriteGistDao.delete(favorite)
    }
}