package com.jeffersonalvess.gists.ui.gists

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jeffersonalvess.network.dto.Gist
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GistViewModelTest {

    @Mock
    private lateinit var dataSourceFactory: DataSource.Factory<Int, Gist>

    @Mock
    private lateinit var pageBuilder: LivePagedListBuilder<Int, Gist>

    private lateinit var viewModel: GistsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = GistsViewModel(dataSourceFactory, pageBuilder)
    }

    @Test
    fun `When factory returns items the viewmodel should pass the items and hide the progress indicator`() {
        // TODO tenta completar ou deleta
        whenever(pageBuilder.build()).thenReturn(
            MutableLiveData(
                PagedList.Builder(
                    dataSourceFactory.create(),
                    1
                ).build()
            )
        )
    }

}