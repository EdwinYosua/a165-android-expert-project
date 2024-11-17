package com.edwinyosua.core.data

import com.edwinyosua.core.data.source.local.LocalDataSource
import com.edwinyosua.core.data.source.remote.RemoteDataSource
import com.edwinyosua.core.data.source.remote.network.ApiResponse
import com.edwinyosua.core.data.source.remote.response.TourismResponse
import com.edwinyosua.core.domain.model.Tourism
import com.edwinyosua.core.domain.repository.ITourismRepository
import com.edwinyosua.core.utils.AppExecutors
import com.edwinyosua.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TourismRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ITourismRepository {

//    companion object {
//        @Volatile
//        private var instance: TourismRepository? = null
//
//        fun getInstance(
//            remoteData: RemoteDataSource,
//            localData: LocalDataSource,
//            appExecutors: AppExecutors
//        ): TourismRepository =
//            instance ?: synchronized(this) {
//                instance ?: TourismRepository(remoteData, localData, appExecutors)
//            }
//    }

    override fun getAllTourism(): Flow<Resource<List<Tourism>>> =
        object :
            NetworkBoundResource<List<Tourism>, List<TourismResponse>>(
                appExecutors
            ) {
            override fun loadFromDB(): Flow<List<Tourism>> =
                localDataSource.getAllTourism().map {
                    DataMapper.mapEntitiesToDomain(it)
                }

            override suspend fun createCall(): Flow<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
            }

            override fun shouldFetch(data: List<Tourism>?): Boolean = true

        }.asFlow()


    override fun getFavoriteTourism(): Flow<List<Tourism>> =
        localDataSource.getFavoriteTourism().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTourism(tourismEntity, state) }
    }
}

