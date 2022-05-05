package com.sample.khadamatfani.model

import com.google.gson.annotations.SerializedName

/**
 * This is same for cooperations, pmethods, working hours and working experiences
 */
data class Same(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String
)