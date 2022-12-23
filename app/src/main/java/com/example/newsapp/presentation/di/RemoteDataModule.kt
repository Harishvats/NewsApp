package com.example.newsapp.presentation.di

import com.example.newsapp.data.api.NewsApiService
import com.example.newsapp.data.repository.dataSource.NewsRemoteDataSource
import com.example.newsapp.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun providesRemoteDataSource(newsApiService: NewsApiService): NewsRemoteDataSource {

        return NewsRemoteDataSourceImpl(newsApiService)
    }
}