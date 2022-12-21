package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.NewsRepository

class RemoveSavedNewsUseCase(private val newsRepository: NewsRepository) {
}