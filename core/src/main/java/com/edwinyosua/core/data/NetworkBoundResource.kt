package com.edwinyosua.core.data

import com.edwinyosua.core.data.source.remote.network.ApiResponse
import com.edwinyosua.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {


    private val result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Empty -> emitAll(loadFromDB().map {
                    Resource.Success(
                        it
                    )
                })

                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }

                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }


//    private val result = MediatorLiveData<Resource<ResultType>>()
//
//    init {
//        result.value = Resource.Loading(null)
//
//        @Suppress("LeakingThis")
//        val dbSource = loadFromDB()
//
//        result.addSource(dbSource) { data ->
//            result.removeSource(dbSource)
//            if (shouldFetch(data)) {
//                fetchFromNetwork(dbSource)
//            } else {
//                result.addSource(dbSource) { newData ->
//                    result.value = Resource.Success(newData)
//                }
//            }
//        }
//    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

//    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
//
//        val apiResponse = createCall()
//
//        result.addSource(dbSource) { newData ->
//            result.value = Resource.Loading(newData)
//        }
//        result.addSource(apiResponse) { response ->
//            result.removeSource(apiResponse)
//            result.removeSource(dbSource)
//            when (response) {
//                is ApiResponse.Success ->
//                    mExecutors.diskIO().execute {
//                        saveCallResult(response.data)
//                        mExecutors.mainThread().execute {
//                            result.addSource(loadFromDB()) { newData ->
//                                result.value = Resource.Success(newData)
//                            }
//                        }
//                    }
//
//                is ApiResponse.Empty -> mExecutors.mainThread().execute {
//                    result.addSource(loadFromDB()) { newData ->
//                        result.value = Resource.Success(newData)
//                    }
//                }
//
//                is ApiResponse.Error -> {
//                    onFetchFailed()
//                    result.addSource(dbSource) { newData ->
//                        result.value = Resource.Error(response.errorMessage, newData)
//                    }
//                }
//            }
//        }
//    }
//
//    fun asLiveData(): LiveData<Resource<ResultType>> = result

    fun asFlow(): Flow<Resource<ResultType>> = result
}