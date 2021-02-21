package com.jeffersonalvess.gists.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jeffersonalvess.network.dto.Gist

class DetailsViewModel(
    private val data: Gist
) : ViewModel() {

    private val _gist = MutableLiveData<Gist>().apply {
        value = data
    }

    val gist: LiveData<Gist> = _gist
}