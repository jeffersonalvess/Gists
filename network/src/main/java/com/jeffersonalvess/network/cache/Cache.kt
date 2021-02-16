package com.jeffersonalvess.network.cache

import com.jeffersonalvess.network.dto.Gist

interface Cache {

    fun getGistsPage(page: Int): List<Gist>?

    fun getGist(gistId: Int): Gist?

    fun addGistsPage(page: Int, gistChunk: List<Gist>)

    fun addGist(gistId: Int, gist: Gist)
}
