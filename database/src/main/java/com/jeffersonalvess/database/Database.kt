package com.jeffersonalvess.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jeffersonalvess.database.dao.FavoriteGistDao
import com.jeffersonalvess.database.entities.Favorites

@Database(entities = [Favorites::class], version = 1, exportSchema = false)
internal abstract class Database : RoomDatabase() {
    abstract val favoriteGistDao: FavoriteGistDao
}
