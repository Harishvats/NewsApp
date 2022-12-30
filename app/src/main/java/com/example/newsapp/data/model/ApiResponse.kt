package com.example.newsapp.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ApiResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)