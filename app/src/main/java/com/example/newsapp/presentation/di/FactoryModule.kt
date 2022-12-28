package com.example.newsapp.presentation.di

import android.app.Application
import com.example.newsapp.domain.usecase.GetNewsHeadLineUseCase
import com.example.newsapp.domain.usecase.GetSearchedNewsUseCase
import com.example.newsapp.presentation.viewModel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        app: Application,
        getNewsHeadLineUseCase: GetNewsHeadLineUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(app, getNewsHeadLineUseCase, getSearchedNewsUseCase)
    }
}