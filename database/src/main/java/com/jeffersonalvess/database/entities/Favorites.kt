package com.jeffersonalvess.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorites (
    @PrimaryKey val name: String,
    val image: String,
)
