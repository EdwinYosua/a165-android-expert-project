package com.dicoding.tourismapp.core.data.source.remote.network

//object ApiConfig {
//
//    private fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
//        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//        .connectTimeout(120, TimeUnit.SECONDS)
//        .readTimeout(120, TimeUnit.SECONDS)
//        .build()
//
//
//    fun provideApiService(): ApiService {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://tourism-api.dicoding.dev/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(provideHttpClient())
//            .build()
//        return retrofit.create(ApiService::class.java)
//    }
//}