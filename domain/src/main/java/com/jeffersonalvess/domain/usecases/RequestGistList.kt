package com.jeffersonalvess.domain.usecases

import com.jeffersonalvess.domain.repository.GistRepository
import com.jeffersonalvess.network.dto.Gist
import io.reactivex.Single

class RequestGistList(
    private val gistRepository: GistRepository
): UseCase<RequestGistList.Param, Single<List<Gist>>> {

    override fun run(param: Param): Single<List<Gist>> =
        gistRepository.getAllGists(param.itemsPerPage, param.page)

    data class Param(
        val itemsPerPage: Int,
        val page: Int
    )
}