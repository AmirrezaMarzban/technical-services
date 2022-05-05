package com.sample.khadamatfani.utils

import android.content.Context
import android.content.SharedPreferences
import com.sample.khadamatfani.G

object SharedPreferencesHelper {
    private const val SHARED = "user"
    private var sharedPreferences: SharedPreferences = G.appContext.getSharedPreferences(SHARED, Context.MODE_PRIVATE)
    private val shEditor = sharedPreferences.edit()

    fun getString(key: String) = sharedPreferences.getString(key, "")

    fun putString(key: String, value:String?) {
        shEditor.putString(key, value).commit()
    }

    fun remove(key: String) {
        shEditor.remove(key).commit()
    }
}