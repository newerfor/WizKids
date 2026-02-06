package com.example.wizkids

import android.app.Application
import com.example.wizkids.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(AppModule.module)
        }
    }
}
