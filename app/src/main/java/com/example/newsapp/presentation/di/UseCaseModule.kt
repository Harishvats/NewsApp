package com.example.newsapp.presentation.di

import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecase.GetNewsHeadLineUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideNewsHeadlineUseCase(newsRepository: NewsRepository): GetNewsHeadLineUseCase {
        return GetNewsHeadLineUseCase(newsRepository)
    }
}