package com.sample.khadamatfani.di

import com.google.gson.GsonBuilder
import com.sample.khadamatfani.api.HttpRequestsUrl
import com.sample.khadamatfani.utils.Constants
import com.sample.khadamatfani.utils.SharedPreferencesHelper
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { GsonBuilder().create() }

    single {
        OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            addInterceptor(Interceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                requestBuilder.header("Accept", "application/json")
                if(SharedPreferencesHelper.getString(Constants.TOKEN)!!.isNotEmpty())
                    requestBuilder.header("Authorization", "Bearer ${SharedPreferencesHelper.getString(Constants.TOKEN)}")
                chain.proceed(requestBuilder.build())
            })
        }.build()
    }

    single {
        Retrofit.Builder()
                .baseUrl(HttpRequestsUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(get()))
                .client(get())
                .build()
    }
}
