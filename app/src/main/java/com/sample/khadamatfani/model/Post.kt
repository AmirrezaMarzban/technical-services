package com.sample.khadamatfani.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("user_phone") val user_phone: String,
    @SerializedName("category") val category: String,
    @SerializedName("description") val description: String,
    @SerializedName("precedent") val precedent: String,
    @SerializedName("cooperation") val cooperation: String,
    @SerializedName("pmethod") val pmethod: String,
    @SerializedName("workinghours") val workinghours: String,
    @SerializedName("working_experience") val working_experience: String,
    @SerializedName("insurance") val insurance: String,
    @SerializedName("remote") val remote: String,
    @SerializedName("military_service") val military_service: String,
    @SerializedName("view") val view: String,
    @SerializedName("status") val status: Int,
    @SerializedName("location") val location: String,
    @SerializedName("created_at") val created_at: String,
)