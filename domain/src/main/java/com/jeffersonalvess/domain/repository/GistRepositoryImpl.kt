package com.jeffersonalvess.domain.repository

import com.jeffersonalvess.network.api.GistApi
import com.jeffersonalvess.network.cache.Cache
import com.jeffersonalvess.network.dto.Gist
import io.reactivex.Single

class GistRepositoryImpl (
    private val cache: Cache,
    private val api: GistApi
) : GistRepository {

    override fun getAllGists(itemsPerPage: Int, page: Int): Single<List<Gist>> {
        val cachedValue = cache.getGistsPage(page)

        return cachedValue?.let {
            Single.just(cachedValue)
        } ?: api.getAllGists(itemsPerPage, page).doOnSuccess { cache.addGistsPage(page, it) }
    }

    override fun getGist(gistId: Int): Single<Gist> {
        val cachedValue = cache.getGist(gistId)

        return cachedValue?.let {
            Single.just(cachedValue)
        } ?: api.getGist(gistId).doOnSuccess { cache.addGist(gistId, it) }
    }
}