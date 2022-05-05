package com.sample.khadamatfani.api
import com.sample.khadamatfani.api.Status.*

enum class Status {
    SUCCESS,
    LOADING,
    ERROR
}

data class Resources<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resources<T> = Resources(SUCCESS, data,null)

        fun <T> error(data: T?, message: String): Resources<T> = Resources(ERROR, data, message)

        fun <T> loading(data: T?): Resources<T> = Resources(LOADING, data, null)
    }
}