package com.example.newsapp.data.repository.dataSource

import com.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticleToDb(article: Article)
    fun getSavedArticle(): Flow<List<Article>>
    suspend fun deleteSavedArticle(article: Article)
}