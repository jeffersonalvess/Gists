package com.jeffersonalvess.gists.di

import com.jeffersonalvess.gists.ui.details.DetailsViewModel
import com.jeffersonalvess.gists.ui.gists.GistsViewModel
import com.jeffersonalvess.network.dto.Gist
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { params -> GistsViewModel(get { params }) }

    viewModel { (gist: Gist) -> DetailsViewModel(gist) }
}
