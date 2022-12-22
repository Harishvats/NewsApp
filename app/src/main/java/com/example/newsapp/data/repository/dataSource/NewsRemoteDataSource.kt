package com.example.newsapp.data.repository.dataSource

import com.example.newsapp.data.model.ApiResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(): Response<ApiResponse>
}