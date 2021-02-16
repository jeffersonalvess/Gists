package com.jeffersonalvess.domain.usecases

import com.jeffersonalvess.domain.repository.GistRepository
import com.jeffersonalvess.network.dto.Gist
import io.reactivex.Single

class RequestGist(
    private val gistRepository: GistRepository
): UseCase<RequestGist.Param, Single<Gist>> {

    override fun run(param: Param): Single<Gist> =
        gistRepository.getGist(param.gistId)

    data class Param(
        val gistId: Int
    )
}