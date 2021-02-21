package com.jeffersonalvess.gists.ui.gists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jeffersonalvess.network.dto.Gist

class GistsViewModel(
    dataSourceFactory: DataSource.Factory<Int, Gist>
) : ViewModel() {

    val gistList: LiveData<PagedList<Gist>>

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(1)
            .setEnablePlaceholders(true)
            .build()

        gistList = LivePagedListBuilder(dataSourceFactory, pagedListConfig).build()
    }

}