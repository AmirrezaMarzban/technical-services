package com.sample.khadamatfani.api.response

import com.google.gson.annotations.SerializedName

data class OtpResponse(
    @SerializedName("message") var message: String,
    @SerializedName("token") var token: String,
    @SerializedName("status") var status: String,
)