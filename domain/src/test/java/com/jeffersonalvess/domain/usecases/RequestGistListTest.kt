package com.jeffersonalvess.domain.usecases

import com.jeffersonalvess.domain.repository.GistRepository
import com.jeffersonalvess.domain.repository.GistRepositoryImpl
import com.jeffersonalvess.network.dto.Files
import com.jeffersonalvess.network.dto.Gist
import com.jeffersonalvess.network.dto.GistOwner
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RequestGistListTest {

    @Mock private lateinit var repository: GistRepository

    private lateinit var usecase: RequestGistList

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this);
        usecase = RequestGistList(repository)
    }

    @Test
    fun `Use case should just replay the repository response`() {
        whenever(repository.getAllGists(any(), any())).thenReturn(Single.just(GIST_RESPONSE))

        usecase.run(RequestGistList.Param(ITEMS_PER_PAGE, PAGE))
            .test()
            .assertResult(GIST_RESPONSE)
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