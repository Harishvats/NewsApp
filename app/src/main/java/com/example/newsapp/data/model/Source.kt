package com.example.newsapp.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Source(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)