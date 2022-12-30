package com.example.newsapp.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.ApiResponse
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.util.Resource
import com.example.newsapp.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val app: Application,
    private val getNewsHeadLineUseCase: GetNewsHeadLineUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase,
    private val saveNewsUseCase: SaveNewsUseCase,
    private val getSavedNewsUseCase: GetSavedNewsUseCase,
    private val deleteSavedNewsUseCase: DeleteSavedNewsUseCase
) : AndroidViewModel(app) {

    val newsHeadLines: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()
    val searchedNews: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()


    fun getNewsHeadlines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO) {
        newsHeadLines.postValue(Resource.Loading())
        try {
            if (isInternetAvailable(app)) {
                val apiResult = getNewsHeadLineUseCase.execute(country, page)
                newsHeadLines.postValue(apiResult)
            } else {
                newsHeadLines.postValue(Resource.Error("Internet is not available"))


            }
        } catch (e: Exception) {
            newsHeadLines.postValue(Resource.Error(e.message.toString()))

        }
    }

    private fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        return result
    }

    fun searchNews(country: String, query: String, page: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            searchedNews.postValue(Resource.Loading())
            try {
                if (isInternetAvailable(app)) {
                    val apiResult = getSearchedNewsUseCase.execute(country, query, page)
                    searchedNews.postValue(apiResult)
                } else {
                    searchedNews.postValue(Resource.Error("Internet is not available"))
                }
            } catch (e: Exception) {
                searchedNews.postValue(Resource.Error(e.message.toString()))

            }
        }

    fun saveArticle(article: Article) {
        viewModelScope.launch {
            saveNewsUseCase.execute(article)
        }
    }

    fun getSavedNews() = liveData {
        getSavedNewsUseCase.execute().collect {
            emit(it)
        }
    }

    fun deleteSavedNews(article: Article) {
        viewModelScope.launch { deleteSavedNewsUseCase.execute(article) }
    }
}