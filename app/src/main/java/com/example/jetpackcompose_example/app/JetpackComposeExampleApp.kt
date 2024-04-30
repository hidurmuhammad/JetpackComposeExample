package com.example.jetpackcompose_example.app

import android.app.Application
import com.example.jetpackcompose_example.di.apiServiceModule
import com.example.jetpackcompose_example.di.networkModule
import com.example.jetpackcompose_example.di.remoteDataSourceModule
import com.example.jetpackcompose_example.di.repositoryModule
import com.example.jetpackcompose_example.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class JetpackComposeExampleApp : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@JetpackComposeExampleApp)
            androidLogger()
            modules(networkModule,
                apiServiceModule, remoteDataSourceModule, repositoryModule, viewModelModule)
        }
    }
}