package com.jeffersonalvess.domain.datasource

import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GistListDataSourceFactoryTest {

    @Mock
    private lateinit var gistListDataSource: GistListDataSource
    private lateinit var factory: GistListDataSourceFactory

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        factory = GistListDataSourceFactory(gistListDataSource)
    }

    @Test
    fun `Create should return the data source`() {
        val dataSource = factory.create()
        assertEquals(dataSource, gistListDataSource)
    }

    @Test
    fun `Retry should call the data source retry`() {
        factory.retry()
        verify((gistListDataSource), times(1)).retry()
    }
}