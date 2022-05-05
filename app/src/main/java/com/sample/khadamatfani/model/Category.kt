package com.sample.khadamatfani.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("background") val background: String,
    @SerializedName("count") val count: Int,
    @SerializedName("posts") val posts: List<Post> = emptyList(),
)
