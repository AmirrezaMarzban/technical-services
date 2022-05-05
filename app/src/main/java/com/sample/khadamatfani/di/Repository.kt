package com.sample.khadamatfani.di

import com.sample.khadamatfani.repository.LoginRepository
import com.sample.khadamatfani.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { MainRepository(get()) }
    factory { LoginRepository(get()) }
}