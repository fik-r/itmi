package com.mobile.itmi

import android.app.Application
import com.mobile.itmi.di.appModule
import com.mobile.itmi.di.domainModule
import com.mobile.itmi.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule + networkModule + domainModule)
        }
    }
}