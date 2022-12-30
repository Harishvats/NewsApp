package com.example.newsapp.presentation.di

import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecase.*
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

    @Singleton
    @Provides
    fun provideSearchedNewsHeadlineUseCase(newsRepository: NewsRepository): GetSearchedNewsUseCase {
        return GetSearchedNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideSaveNewsUseCase(newsRepository: NewsRepository): SaveNewsUseCase {
        return SaveNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSavedNewsUseCase(newsRepository: NewsRepository): GetSavedNewsUseCase {
        return GetSavedNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetDeleteSavedNewsUseCase(newsRepository: NewsRepository): DeleteSavedNewsUseCase {
        return DeleteSavedNewsUseCase(newsRepository)
    }


}