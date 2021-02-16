package com.jeffersonalvess.network.cache

import com.jeffersonalvess.network.dto.Gist

class CacheImpl: Cache {
    private val cache = mutableMapOf<String, Any?>()

    override fun getGistsPage(page: Int): List<Gist>? {
        val key = getKey(GIST_LIST_KEY, page)
        return (cache[key] as? List<*>)?.filterIsInstance<Gist>()
    }

    override fun getGist(gistId: Int): Gist? {
        val key = getKey(GIST_KEY, gistId)
        return cache[key] as? Gist
    }

    override fun addGistsPage(page: Int, gistChunk: List<Gist>) {
        val key = getKey(GIST_LIST_KEY, page)
        cache[key] = gistChunk
    }

    override fun addGist(gistId: Int, gist: Gist) {
        val key = getKey(GIST_KEY, gistId)
        cache[key] = gist
    }

    private fun getKey(base: String, identifier: Int) = "$base.$identifier"

    companion object {
        private const val GIST_LIST_KEY = "gist_list_key"
        private const val GIST_KEY = "gist_key"
    }
}