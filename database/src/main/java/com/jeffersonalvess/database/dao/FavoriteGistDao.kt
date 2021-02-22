package com.jeffersonalvess.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.jeffersonalvess.database.entities.Favorites

@Dao
interface FavoriteGistDao {

    @Query("SELECT * FROM favorites")
    fun findAll(): LiveData<List<Favorites>>

    @Insert(onConflict=REPLACE)
    fun insert(Favorites: Favorites)

    @Delete
    fun delete(Favorites: Favorites)
}
