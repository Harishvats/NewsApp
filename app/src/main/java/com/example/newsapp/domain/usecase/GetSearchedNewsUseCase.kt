package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.ApiResponse
import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(searchQuery: String): Resource<ApiResponse> {
        return newsRepository.getSearchedNews(searchQuery)
    }
}