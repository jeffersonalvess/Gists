package com.jeffersonalvess.domain.datasource

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.jeffersonalvess.network.dto.Gist

class GistListDataSourceFactory(
    private val gistListDataSource: PageKeyedDataSource<Int, Gist>
) : DataSource.Factory<Int, Gist>() {

    override fun create(): DataSource<Int, Gist> {
        return gistListDataSource
    }

    fun retry() {
        (gistListDataSource as GistListDataSource).retry()
    }

    fun finalize() {
        (gistListDataSource as GistListDataSource).finalize()
    }
}