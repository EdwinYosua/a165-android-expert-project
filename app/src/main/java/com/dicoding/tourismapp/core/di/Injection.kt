package com.dicoding.tourismapp.core.di

//
//object Injection {
//    fun provideRepository(context: Context): TourismRepository {
//        val database = TourismDatabase.getInstance(context)
//
//        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
//        val appExecutors = AppExecutors()
//
//        return TourismRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
//    }
//
//
//    fun provideUseCase(context: Context): TourismUseCase {
//        val repository = provideRepository(context)
//        return TourismInteractor(repository)
//    }
//}
