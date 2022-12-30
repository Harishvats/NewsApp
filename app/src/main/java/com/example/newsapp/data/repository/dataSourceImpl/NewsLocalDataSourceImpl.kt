package com.example.newsapp.data.repository.dataSourceImpl

import com.example.newsapp.data.db.ArticleDao
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(private val articleDao: ArticleDao) : NewsLocalDataSource {
    override suspend fun saveArticleToDb(article: Article) {
        articleDao.insert(article)
    }

    override fun getSavedArticle(): Flow<List<Article>> {
        return articleDao.getSavedArticle()
    }

    override suspend fun deleteSavedArticle(article: Article) {
        articleDao.deleteSavedArticle(article)
    }

}