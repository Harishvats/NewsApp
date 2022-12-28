package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.ApiResponse
import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, searchQuery: String, page: Int): Resource<ApiResponse> {
        return newsRepository.getSearchedNews(country, searchQuery, page)
    }
}