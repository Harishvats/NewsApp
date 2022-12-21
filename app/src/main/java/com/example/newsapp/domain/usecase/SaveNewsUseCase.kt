package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.NewsRepository

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
}