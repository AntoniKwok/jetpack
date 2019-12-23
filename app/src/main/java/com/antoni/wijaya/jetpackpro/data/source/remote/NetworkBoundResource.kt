package com.antoni.wijaya.jetpackpro.data.source.remote

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.antoni.wijaya.jetpackpro.data.source.remote.response.ApiResponse
import com.antoni.wijaya.jetpackpro.data.source.remote.response.StatusResponse
import com.antoni.wijaya.jetpackpro.utils.AppExecutors
import com.antoni.wijaya.jetpackpro.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(
    private val appExecutors: AppExecutors
) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val dbSource: LiveData<ResultType> = loadFromDB()

        result.addSource(dbSource) { data ->
            run {
                result.removeSource(dbSource)
                if (shouldFetch(data))
                    fetchFromNetwork(dbSource)
                else
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.success(newData)
                    }
            }
        }

    }

    @SuppressLint("VisibleForTests")
    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(
            dbSource
        ) { data ->
            result.value = Resource.loading(data)
        }

        apiResponse?.let { data ->
            result.addSource(data) { res ->
                result.removeSource(data)
                result.removeSource(dbSource)

                when (res.statusResponse) {
                    StatusResponse.SUCCESS -> {
                        appExecutors.diskIO().execute {
                            saveCallResult(res.body)

                            appExecutors.mainThread().execute {
                                result.addSource(
                                    loadFromDB()
                                ) { data ->
                                    result.value = Resource.success(data)
                                }
                            }
                        }
                    }
                    StatusResponse.EMPTY -> {
                        appExecutors.diskIO().execute {
                            result.addSource(loadFromDB()) { data ->
                                result.value = Resource.success(data)
                            }
                        }
                    }
                    StatusResponse.ERROR -> {
                        onFetchFailed()
                        result.addSource(loadFromDB()) { data ->
                            result.value = res.message?.let { Resource.error(it, data) }
                        }
                    }
                }
            }
        }

    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    private fun onFetchFailed() {

    }

    abstract fun saveCallResult(body: RequestType)

    abstract fun createCall(): LiveData<ApiResponse<RequestType>>?

    abstract fun shouldFetch(data: ResultType): Boolean

    abstract fun loadFromDB(): LiveData<ResultType>


}