package com.jeffersonalvess.domain.di

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.jeffersonalvess.domain.repository.GistRepository
import com.jeffersonalvess.domain.usecases.RequestGist
import com.jeffersonalvess.domain.usecases.RequestGistList
import com.jeffersonalvess.domain.usecases.UseCase
import com.jeffersonalvess.network.dto.Gist
import io.reactivex.Single
import org.koin.dsl.module

val domainModule = module {

    // Provides an instance of [GistRepository]
    single { provideGistRepository(get(), get()) as GistRepository }

    // Provides an instance of [RequestGistList]
    single { provideRequestGistList(get()) as UseCase<RequestGistList.Param, Single<List<Gist>>>  }

    // Provides an instance of [RequestGist]
    // single<UseCase<RequestGist.Param, Single<Gist>>> { provideRequestGist(get()) }

    // Provides an instance of [GistListDataSource]
    single { //(onErrorCallback: () -> Unit, onSuccessCallback: () -> Unit) ->
        provideGistListDataSource(get()/*, onErrorCallback, onSuccessCallback*/) as PageKeyedDataSource<Int, Gist>
    }

    // Provides an instance of [GistListDataSourceFactory]
    single { providesGistListDataSourceFactory(get()) as DataSource.Factory<Int, Gist> }
}

