package com.jeffersonalvess.domain.repository

import android.util.Log
import com.jeffersonalvess.domain.datasource.GistListDataSource
import com.jeffersonalvess.network.api.GistApi
import com.jeffersonalvess.network.cache.Cache
import com.jeffersonalvess.network.dto.Gist
import io.reactivex.Single

class GistRepositoryImpl (
    private val cache: Cache,
    private val api: GistApi,
    private val onErrorCallback: () -> Unit
) : GistRepository {

    override fun getAllGists(itemsPerPage: Int, page: Int): Single<List<Gist>> {
        val cachedValue = cache.getGistsPage(page)

        return cachedValue?.let {
            Single.just(cachedValue)
        } ?: api.getAllGists(itemsPerPage, page)
            .doOnSuccess { cache.addGistsPage(page, it) }
            .doOnError { handleError(it) }
    }

    override fun getGist(gistId: Int): Single<Gist> {
        val cachedValue = cache.getGist(gistId)

        return cachedValue?.let {
            Single.just(cachedValue)
        } ?: api.getGist(gistId)
            .doOnSuccess { cache.addGist(gistId, it) }
            .doOnError { handleError(it) }
    }

    private fun handleError(th: Throwable) {
        Log.e(TAG, "Failed to load gists", th)
        onErrorCallback()
    }

    companion object {
        private const val TAG = "GistRepository"
    }
}