package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.NewsRepository

class GetDetailedNewsUseCase(private val newsRepository: NewsRepository) {
}