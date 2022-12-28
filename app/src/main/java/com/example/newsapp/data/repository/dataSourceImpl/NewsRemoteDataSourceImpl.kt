package com.example.newsapp.data.repository.dataSourceImpl

import com.example.newsapp.data.api.NewsApiService
import com.example.newsapp.data.model.ApiResponse
import com.example.newsapp.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(private val newsApiService: NewsApiService) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(
        country: String, page: Int
    ): Response<ApiResponse> {

        return newsApiService.getTopHeadlines(country, page)
    }

    override suspend fun getSearchedTopHeadlines(
        country: String, searchQuery: String, page: Int
    ): Response<ApiResponse> {
        return newsApiService.getSearchedTopHeadlines(country, searchQuery, page)
    }
}