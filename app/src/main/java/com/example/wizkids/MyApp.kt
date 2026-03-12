package com.example.wizkids

import android.app.Application
import com.example.core_data.di.dataModule
import com.example.core_domain.di.domainModule
import com.example.core_viewmodel.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                dataModule,
                domainModule,
                viewModelModule
            )
        }
    }
}
