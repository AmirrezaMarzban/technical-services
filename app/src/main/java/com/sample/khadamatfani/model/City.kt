package com.sample.khadamatfani.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)