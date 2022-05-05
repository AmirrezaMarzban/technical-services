package com.sample.khadamatfani

import android.app.Application
import android.content.Context
import com.sample.khadamatfani.di.networkModule
import com.sample.khadamatfani.di.apiModule
import com.sample.khadamatfani.di.repositoryModule
import com.sample.khadamatfani.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class G : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidContext(applicationContext)
            modules(listOf(
                networkModule,
                apiModule,
                viewModelModule,
                repositoryModule
            ))
        }
    }

    companion object {
        lateinit var appContext: Context
    }
}
