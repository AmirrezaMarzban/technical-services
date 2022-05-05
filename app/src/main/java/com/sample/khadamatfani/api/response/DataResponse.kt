package com.sample.khadamatfani.api.response

import com.google.gson.annotations.SerializedName

data class DataResponse<T>(
    @SerializedName("data") var data: List<T>
)