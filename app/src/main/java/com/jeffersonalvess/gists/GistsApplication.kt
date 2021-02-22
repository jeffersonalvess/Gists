package com.jeffersonalvess.gists

import android.app.Application
import com.jeffersonalvess.database.di.databaseModule
import com.jeffersonalvess.domain.di.domainModule
import com.jeffersonalvess.gists.di.appModule
import com.jeffersonalvess.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GistsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger()
            androidContext(this@GistsApplication)
            modules(
                appModule +
                databaseModule+
                domainModule +
                networkModule
            )
        }
    }
}