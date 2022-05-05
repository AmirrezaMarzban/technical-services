package com.sample.khadamatfani.ui.viewmodel

import androidx.lifecycle.liveData
import com.sample.khadamatfani.api.Resources
import com.sample.khadamatfani.repository.LoginRepository
import com.sample.khadamatfani.repository.MainRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class LoginViewModel(private val rep: LoginRepository): BaseViewModel(Main) {

    fun login(phone: String) = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.login(phone)))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }

    fun sendOtpCode(code: Int) = liveData(IO) {
        emit(Resources.loading(null))
        try {
            emit(Resources.success(rep.sendOtpSms(code)))
        } catch (e: Exception) {
            emit(Resources.error(null, e.message ?: "Error Occurred!"))
        }
    }
}