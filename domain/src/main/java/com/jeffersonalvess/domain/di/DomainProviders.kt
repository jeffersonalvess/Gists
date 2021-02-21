package com.jeffersonalvess.domain.di

import androidx.paging.PageKeyedDataSource
import com.jeffersonalvess.domain.datasource.GistListDataSource
import com.jeffersonalvess.domain.datasource.GistListDataSourceFactory
import com.jeffersonalvess.domain.repository.GistRepository
import com.jeffersonalvess.domain.repository.GistRepositoryImpl
import com.jeffersonalvess.domain.usecases.RequestGistList
import com.jeffersonalvess.domain.usecases.UseCase
import com.jeffersonalvess.network.api.GistApi
import com.jeffersonalvess.network.cache.Cache
import com.jeffersonalvess.network.dto.Gist
import io.reactivex.Single

internal fun provideGistRepository(
    cache: Cache,
    api: GistApi,
    onErrorCallback: () -> Unit
) = GistRepositoryImpl(cache, api, onErrorCallback)

internal fun provideRequestGistList(
    gistRepository: GistRepository
) = RequestGistList(gistRepository)

internal fun provideGistListDataSource(
    requestGistList: UseCase<RequestGistList.Param, Single<List<Gist>>>,
    onErrorCallback: () -> Unit
) = GistListDataSource(requestGistList, onErrorCallback)

internal fun providesGistListDataSourceFactory(
    gistListDataSource: PageKeyedDataSource<Int, Gist>
) = GistListDataSourceFactory(gistListDataSource)
