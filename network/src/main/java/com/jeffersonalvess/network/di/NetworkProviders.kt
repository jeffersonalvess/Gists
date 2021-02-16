package com.jeffersonalvess.network.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeffersonalvess.network.api.GistApi
import com.jeffersonalvess.network.api.GistApi.Companion.URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal fun provideWebCache(application: Application): Cache {
    val cacheSize = 1024 * 1024 * 10
    return Cache(application.cacheDir, cacheSize.toLong())
}

internal fun provideOkHttp(cache: Cache) =
    OkHttpClient.Builder().cache(cache).build()

internal fun provideGson() =
    GsonBuilder().create()

internal fun provideRetrofit(okHttp: OkHttpClient, gson: Gson) =
    Retrofit.Builder()
        .baseUrl(URL)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

internal fun provideService(retrofit: Retrofit) =
    retrofit.create(GistApi::class.java)