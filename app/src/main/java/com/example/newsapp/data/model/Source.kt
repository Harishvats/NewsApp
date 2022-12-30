package com.example.newsapp.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)