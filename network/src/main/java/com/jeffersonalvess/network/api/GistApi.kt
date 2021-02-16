package com.jeffersonalvess.network.api

import com.jeffersonalvess.network.dto.Gist
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GistApi {

    @GET("gists/public")
    fun getAllGists(
        @Query(PER_PAGE) itemsPerPage: Int,
        @Query(PAGE) page: Int
    ): Single<List<Gist>>

    @GET("gists/{$GIST_ID}")
    fun getGist(
        @Path(GIST_ID) gistId: Int
    ): Single<Gist>

    companion object {
        const val URL = "https://api.github.com/"

        private const val GIST_ID = "gist_id"
        private const val PER_PAGE = "per_page"
        private const val PAGE = "page"
    }
}
