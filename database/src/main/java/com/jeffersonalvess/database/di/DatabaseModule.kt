package com.jeffersonalvess.database.di

import android.app.Application
import androidx.room.Room
import com.jeffersonalvess.database.Database
import com.jeffersonalvess.database.dao.FavoriteGistDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): Database {
        return Room.databaseBuilder(application, Database::class.java, "gists.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: Database): FavoriteGistDao {
        return database.favoriteGistDao
    }

    single { provideDatabase(androidApplication()) }

    single { provideDao(get()) }
}
