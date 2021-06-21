package com.example.aplikasiwisatarohul.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.aplikasiwisatarohul.data.source.remote.StatusResponse
import com.example.aplikasiwisatarohul.vo.Resource
import com.example.aplikasiwisatarohul.data.source.remote.ApiResponse
import com.example.aplikasiwisatarohul.utils.AppExecutors

abstract class NetworkOnlyResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        fetchFromNetwork()
    }

    private fun onFetchFailed() {}

    protected open fun processResponse(data: RequestType) = data

    protected abstract fun handleCallResult(item: RequestType?): ResultType?

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    private fun fetchFromNetwork() {

        val apiResponse = createCall()

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)

            when (response.status) {
                StatusResponse.SUCCESS ->
                    mExecutors.diskIO().execute {
                        mExecutors.diskIO().execute {
                            val res = handleCallResult(response.body?.let { processResponse(it) })
                            mExecutors.mainThread().execute {
                                result.value = Resource.success(res)
                            }
                        }
                    }
                StatusResponse.EMPTY -> mExecutors.mainThread().execute {
                    result.value = Resource.success(null)
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.value = Resource.error(response.message, null)
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}