package com.jeffersonalvess.domain.repository

import com.jeffersonalvess.network.dto.Gist
import io.reactivex.Observable
import io.reactivex.Single

interface GistRepository {

    fun getAllGists(itemsPerPage: Int, page: Int): Single<List<Gist>>

    fun getGist(gistId: Int): Single<Gist>
}