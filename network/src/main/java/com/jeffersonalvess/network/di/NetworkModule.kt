package com.jeffersonalvess.network.di

import com.jeffersonalvess.network.cache.Cache
import com.jeffersonalvess.network.cache.CacheImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkModule = module {

    //
    factory { provideWebCache(androidApplication()) }
    factory { provideOkHttp(get()) }
    factory { provideGson() }
    factory { provideRetrofit(get(), get()) }

    single<Cache> { CacheImpl() }
    single { provideService(get()) }
}
