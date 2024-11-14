package com.dicoding.tourismapp

import android.app.Application
import com.dicoding.tourismapp.core.di.module.databaseModule
import com.dicoding.tourismapp.core.di.module.networkModule
import com.dicoding.tourismapp.core.di.module.repositoryModule
import com.dicoding.tourismapp.core.di.module.useCaseModule
import com.dicoding.tourismapp.core.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.NONE)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}