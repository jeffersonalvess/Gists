package com.jeffersonalvess.gists.ui.gists

import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jeffersonalvess.domain.datasource.GistListDataSourceFactory
import com.jeffersonalvess.network.dto.Gist

class GistsViewModel(
    private val dataSourceFactory: DataSource.Factory<Int, Gist>
) : ViewModel() {

    private val _showProgress = MutableLiveData<Boolean>()

    val gistList: LiveData<PagedList<Gist>>

    val showProgress: LiveData<Boolean>
        get() = _showProgress

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(1)
            .setEnablePlaceholders(true)
            .build()

        gistList = LivePagedListBuilder(
            dataSourceFactory,
            pagedListConfig
        ).build()
         .map {
            _showProgress.value = it.count() > 0
            it
        }
    }

    fun retry() {
        (dataSourceFactory as GistListDataSourceFactory).retry()
    }

}