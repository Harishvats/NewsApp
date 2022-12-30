package com.example.newsapp.data.repository

import com.example.newsapp.data.model.ApiResponse
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.dataSource.NewsLocalDataSource
import com.example.newsapp.data.repository.dataSource.NewsRemoteDataSource
import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {
    override suspend fun getNewsHeadlines(
        country: String, page: Int
    ): Resource<ApiResponse> {

        return responseToResource(newsRemoteDataSource.getTopHeadlines(country, page))
    }

    override suspend fun getSearchedNews(
        country: String, searchQuery: String, page: Int
    ): Resource<ApiResponse> {
        return responseToResource(
            newsRemoteDataSource.getSearchedTopHeadlines(
                country, searchQuery, page
            )
        )
    }


    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.saveArticleToDb(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDataSource.deleteSavedArticle(article)
    }

    override fun getSavedNews(): Flow<List<Article>> {
        return newsLocalDataSource.getSavedArticle()
    }

    private fun responseToResource(response: Response<ApiResponse>): Resource<ApiResponse> {
        if (response.isSuccessful) {
            response.body()?.let { data ->
                return Resource.Success(data)
            }
        }
        return Resource.Error(response.message())

    }
}