package com.sample.khadamatfani.di

import com.sample.khadamatfani.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(ApiService::class.java) }
}