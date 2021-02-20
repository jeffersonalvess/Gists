package com.jeffersonalvess.gists.di

import com.jeffersonalvess.gists.ui.gists.GistsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { params -> GistsViewModel(get {params}) }
}