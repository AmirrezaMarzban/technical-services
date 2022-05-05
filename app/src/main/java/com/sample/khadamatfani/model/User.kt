package com.sample.khadamatfani.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("location") val location: String,
    @SerializedName("created_at") val created_at: String,
)
