package com.example.newsapp.data.repository.dataSourceImpl

import com.example.newsapp.data.api.NewsApiService
import com.example.newsapp.data.model.ApiResponse
import com.example.newsapp.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService,
    private val country: String,
    private val page: Int
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(): Response<ApiResponse> {

        return newsApiService.getTopHeadlines(country, page)
    }
}