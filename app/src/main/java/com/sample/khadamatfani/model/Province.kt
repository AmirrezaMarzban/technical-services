package com.sample.khadamatfani.model

import com.google.gson.annotations.SerializedName

data class Province(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cities") val cities: List<City>,
)