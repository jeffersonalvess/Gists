package com.jeffersonalvess.domain.di

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.jeffersonalvess.domain.repository.GistRepository
import com.jeffersonalvess.domain.usecases.RequestGistList
import com.jeffersonalvess.domain.usecases.UseCase
import com.jeffersonalvess.network.dto.Gist
import io.reactivex.Single
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val domainModule = module {

    // Provides an instance of [GistRepository]
    single<GistRepository> { provideGistRepository(get(), get()) }

    // Provides an instance of [RequestGistList]
    single<UseCase<RequestGistList.Param, Single<List<Gist>>>> { params ->
        provideRequestGistList(get { params })
    }

    // Provides an instance of [GistListDataSource]
    factory<PageKeyedDataSource<Int, Gist>> { (onErrorCallback: () -> Unit) ->
        provideGistListDataSource( get { parametersOf(onErrorCallback) }, onErrorCallback)
    }

    // Provides an instance of [GistListDataSourceFactory]
    factory<DataSource.Factory<Int, Gist>> { params -> providesGistListDataSourceFactory(get { params }) }
}
