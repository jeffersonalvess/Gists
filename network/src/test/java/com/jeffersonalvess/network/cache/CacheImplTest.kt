package com.jeffersonalvess.network.cache

import com.jeffersonalvess.network.dto.Files
import com.jeffersonalvess.network.dto.Gist
import com.jeffersonalvess.network.dto.GistOwner
import junit.framework.Assert.assertEquals
import org.junit.Test

class CacheImplTest {

    private val cache = CacheImpl()

    @Test
    fun `Verify if gist is saved and returned properly`() {
        cache.addGist(GIST_ID, GIST)
        val response = cache.getGist(GIST_ID)
        assertEquals(response, GIST)
    }

    @Test
    fun `Verify if fetched page is saved and returned properly`() {
        cache.addGistsPage(GIST_PAGE, GIST_RESPONSE)
        val response = cache.getGistsPage(GIST_PAGE)
        assertEquals(response, GIST_RESPONSE)
    }

    companion object {

        private const val GIST_ID = 1

        private const val GIST_PAGE = 1

        private val GIST = Gist(
            id = "id",
            description = "description",
            url = "url",
            creation = "creation",
            owner = GistOwner(
                login = "login",
                avatar = "avatar"
            ),
            files = mapOf(
                "file" to Files(
                    name = "name",
                    type = "type",
                    language = "language",
                    url = "url"
                )
            )
        )

        private val GIST_RESPONSE = listOf(GIST)
    }

}