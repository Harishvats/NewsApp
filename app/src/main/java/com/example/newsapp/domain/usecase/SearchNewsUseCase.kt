package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.NewsRepository

class SearchNewsUseCase(private val newsRepository: NewsRepository) {
}