package com.sample.khadamatfani.di

import com.sample.khadamatfani.ui.viewmodel.LoginViewModel
import com.sample.khadamatfani.ui.viewmodel.MainViewModel
import com.sample.khadamatfani.ui.viewmodel.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    single { LoginViewModel(get()) }
    single { SharedViewModel() }
}