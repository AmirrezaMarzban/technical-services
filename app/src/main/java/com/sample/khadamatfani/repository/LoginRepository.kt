package com.sample.khadamatfani.repository

import com.sample.khadamatfani.api.ApiService

class LoginRepository(private val apiService: ApiService) {
    suspend fun sendOtpSms(code: Int) = apiService.confirmOTP(code)
    suspend fun login(phone: String) = apiService.login(phone)
}