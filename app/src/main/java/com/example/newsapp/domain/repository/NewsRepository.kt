package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.ApiResponse
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadlines(): Resource<ApiResponse>
    suspend fun getSearchedNews(searchQuery: String): Resource<ApiResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>
}