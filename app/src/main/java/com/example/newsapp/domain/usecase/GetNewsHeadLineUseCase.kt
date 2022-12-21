package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.NewsRepository

class GetNewsHeadLineUseCase(private val newsRepository: NewsRepository) {
}