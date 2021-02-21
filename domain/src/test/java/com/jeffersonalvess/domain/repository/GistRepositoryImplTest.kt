package com.jeffersonalvess.domain.repository

import com.jeffersonalvess.network.api.GistApi
import com.jeffersonalvess.network.cache.Cache
import com.jeffersonalvess.network.dto.Files
import com.jeffersonalvess.network.dto.Gist
import com.jeffersonalvess.network.dto.GistOwner
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GistRepositoryImplTest {

    @Mock
    private lateinit var cache: Cache

    @Mock
    private lateinit var api: GistApi

    private lateinit var repository: GistRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
        repository = GistRepositoryImpl(cache, api)
    }

    @Test
    fun `Fetch all gists should save the result on cache and return the object`() {
        whenever(cache.getGistsPage(any())).thenReturn(null)
        whenever(api.getAllGists(any(), any())).thenReturn(Single.just(GIST_RESPONSE))
        val response = repository
            .getAllGists(ITEMS_PER_PAGE, PAGE)
            .test()

        //Verify if cache saves the value
        verify(cache, times(1)).addGistsPage(any(), any())

        //Verify if the response is the expected one
        response.assertResult(GIST_RESPONSE)
    }

    @Test
    fun `Fetch gist should save the result on cache and return the object`() {
        whenever(cache.getGist(any())).thenReturn(null)
        whenever(api.getGist(any())).thenReturn(Single.just(GIST))
        val response = repository
            .getGist(PAGE)
            .test()

        //Verify if cache saves the value
        verify(cache, times(1)).addGist(any(), any())

        //Verify if the response is the expected one
        response.assertResult(GIST)
    }

    @Test
    fun `If gist page is cached so the cache should be returned and the call omitted`() {
        whenever(cache.getGistsPage(any())).thenReturn(GIST_RESPONSE)

        val response = repository
            .getAllGists(ITEMS_PER_PAGE, PAGE)
            .test()

        //Verify if api wasn't called
        verify(api, never()).getAllGists(any(), any())

        //Verify if the response is the expected one
        response.assertResult(GIST_RESPONSE)
    }

    @Test
    fun `If gist is cached so the cache should be returned and the call omitted`() {
        whenever(cache.getGist(any())).thenReturn(GIST)

        val response = repository
            .getGist(PAGE)
            .test()

        //Verify if api wasn't called
        verify(api, never()).getGist(any())

        //Verify if the response is the expected one
        response.assertResult(GIST)
    }

    companion object {

        private const val ITEMS_PER_PAGE = 30
        private const val PAGE = 1

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