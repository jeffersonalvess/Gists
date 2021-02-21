package com.jeffersonalvess.domain.datasource

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.jeffersonalvess.domain.usecases.RequestGistList
import com.jeffersonalvess.domain.usecases.UseCase
import com.jeffersonalvess.network.dto.Gist
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@SuppressLint("LongLogTag")
class GistListDataSource(
    private val requestGistList: UseCase<RequestGistList.Param, Single<List<Gist>>>,
    private val onErrorCallback: () -> Unit
) : PageKeyedDataSource<Int, Gist>() {

    private var disposable: Disposable? = null
    private var retryCallback: (() -> Unit)? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Gist>
    ) {
        disposable = requestGistList.run(RequestGistList.Param(ITEMS_PER_PAGE, 0))
            .subscribe({ response ->
                val result = sendResultOrTriggerError(response)
                if (result.isNotEmpty()) {
                    callback.onResult(response, null, 1)
                }
            }, { error ->
                Log.e(TAG, "Failed to load gists", error)
                onErrorCallback()
                retryCallback = { loadInitial(params, callback) }
            })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Gist>) {
        if (params.key <= TOTAL_PAGES) {
            disposable = requestGistList.run(RequestGistList.Param(ITEMS_PER_PAGE, params.key))
                .retryWhen { it.delay(5, TimeUnit.SECONDS) }
                .subscribe({ response ->
                    val result = sendResultOrTriggerError(response)
                    if (result.isNotEmpty()) {
                        callback.onResult(response, params.key + 1)
                    }
                }, { error ->
                    Log.e(TAG, "Failed to load gists", error)
                    retryCallback = { loadAfter(params, callback) }
                })
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Gist>) {}

    fun finalize() {
        disposable?.dispose()
    }

    fun retry() {
        retryCallback?.invoke()
    }

    private fun sendResultOrTriggerError(response: List<Gist>): List<Gist> {
        if (response.isNullOrEmpty()) {
            onErrorCallback()
        }

        return response
    }

    companion object {
        private const val TAG = "GistListDataSourceFactory"

        private const val ITEMS_PER_PAGE = 30

        private const val TOTAL_PAGES = 100
    }
}