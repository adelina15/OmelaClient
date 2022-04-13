package com.example.omela.application

import android.app.Application
import com.example.omela.koin.retrofitModule
import com.example.omela.koin.viewModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(retrofitModule, viewModules))
            androidContext(this@MyApplication)
        }
    }
}